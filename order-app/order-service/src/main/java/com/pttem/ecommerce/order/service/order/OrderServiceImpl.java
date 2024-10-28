/* SmartICT Bilisim A.S. (C) 2021 */
package com.pttem.ecommerce.order.service.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pttem.ecommerce.order.constant.enums.EnumCrudExceptionMessages;
import com.pttem.ecommerce.order.constant.enums.EnumOrderStatus;
import com.pttem.ecommerce.order.constant.exceptions.ServiceException;
import com.pttem.ecommerce.order.dao.order.OrderRepository;
import com.pttem.ecommerce.order.domain.OrderEntity;
import com.pttem.ecommerce.order.dto.order.OrderDto;
import com.pttem.ecommerce.order.dto.order.OrderTopicDto;
import com.pttem.ecommerce.order.service.base.BaseCrudServiceImpl;
import com.pttem.ecommerce.order.service.mapper.order.OrderMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl extends BaseCrudServiceImpl<OrderEntity, OrderDto> implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final ObjectMapper objectMapper;

    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository, OrderMapper.INSTANCE);
        this.orderRepository = orderRepository;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    @Transactional
    public OrderDto placeOrder(OrderDto orderDto) {
        //userControl(orderDto);

        orderDto.setOrderStatus(EnumOrderStatus.RECEIVED);
        OrderDto savedOrderDto = super.create(orderDto);

        try {
            String orderTopicStr = objectMapper.writeValueAsString(new OrderTopicDto(savedOrderDto));
            kafkaTemplate.send(this.topic, orderTopicStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return orderDto;
    }

    private void userControl(OrderDto orderDto) {
        //Eureka üzerinden erişim ile diğer servise sorgu atılıyor.
        ResponseEntity response = restTemplate.getForObject("http://host.docker.internal:7002/user/isUserExist/"+ orderDto.getCustomerId(), ResponseEntity.class);
        assert response != null;
        Boolean isUserExists= (Boolean) response.getBody();
        if(Boolean.FALSE.equals(isUserExists)){
            throw new ServiceException(EnumCrudExceptionMessages.NO_SUCH_USER);
        }
    }


    @KafkaListener(topics = "${spring.kafka.consumer.success-topic}", groupId = "order-app-group")
    public void listenToSuccessfullStock(String orderTopic) {
        try {
            OrderTopicDto orderTopicDto = this.objectMapper.readValue(orderTopic, OrderTopicDto.class);
            OrderDto orderDto = super.read(orderTopicDto.getOrderId());
            orderDto.setOrderStatus(EnumOrderStatus.COMPLETED);
            super.update(orderDto);
        } catch (ObjectOptimisticLockingFailureException optimisticEntityLockException) {
            LOGGER.error("Successfull update stock process ObjectOptimisticLockingFailureException.Details: ->{}", optimisticEntityLockException.getLocalizedMessage());
        } catch (Exception e) {
            LOGGER.error("Successfull from stock optimistic general exception.Details: ->{}", e.getLocalizedMessage());
        }
    }


    @KafkaListener(topics = "${spring.kafka.consumer.failed-topic}", groupId = "order-app-group")
    public void listenToFailedStockUpdate(String orderTopic) {
        try {
            OrderTopicDto orderTopicDto = this.objectMapper.readValue(orderTopic, OrderTopicDto.class);
            OrderDto orderDto = super.read(orderTopicDto.getOrderId());
            orderDto.setOrderStatus(EnumOrderStatus.OUT_OF_STOCK);
            super.update(orderDto);
        } catch (ObjectOptimisticLockingFailureException optimisticEntityLockException) {
            LOGGER.error("Failed update stock process ObjectOptimisticLockingFailureException. Details: ->{}", optimisticEntityLockException.getLocalizedMessage());
        } catch (Exception e) {
            LOGGER.error("Failed update stock process general exception.Details: ->{}", e.getLocalizedMessage());
        }
    }

}
