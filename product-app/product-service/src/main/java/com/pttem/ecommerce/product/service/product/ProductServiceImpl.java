
package com.pttem.ecommerce.product.service.product;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pttem.ecommerce.product.constant.enums.EnumExceptionMessages;
import com.pttem.ecommerce.product.constant.exceptions.ServiceException;
import com.pttem.ecommerce.product.dao.product.ProductRepository;
import com.pttem.ecommerce.product.domain.base.ProductEntity;
import com.pttem.ecommerce.product.dto.product.OrderTopicDto;
import com.pttem.ecommerce.product.dto.product.ProductDto;
import com.pttem.ecommerce.product.service.base.BaseCrudServiceImpl;
import com.pttem.ecommerce.product.service.mapper.product.ProductMapper;
import jakarta.transaction.Transactional;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseCrudServiceImpl<ProductEntity, ProductDto> implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Value("${spring.kafka.producer.failed-topic}")
    private String failTopic;

    @Value("${spring.kafka.producer.success-topic}")
    private String successTopic;

    private final ObjectMapper objectMapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository, ProductMapper.INSTANCE);
        this.productRepository = productRepository;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Transactional
    @Override
    public void updateProductStock(Long productId, Long newStock) {
        LOGGER.info("Update Product Stock Service Method. Product : {}, newStock:{}", productId, newStock);
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(EnumExceptionMessages.OBJECT_CANNOT_FOUND));
        product.setStock(newStock);
        productRepository.save(product);
        LOGGER.info("Update Product Stock Service Method Enc");
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "product-app-group")
    public void listenToOrderCreatedUpdates(String orderTopic) {
        try {
            LOGGER.debug("Listener Order Create Success Topic Method with: {}", orderTopic);
            OrderTopicDto orderTopicDto = this.objectMapper.readValue(orderTopic, OrderTopicDto.class);

            dropFromStock(orderTopicDto);
            kafkaTemplate.send(this.successTopic, orderTopic);
            LOGGER.debug("Listener Order Create Success Topic Method End");
        } catch (ObjectOptimisticLockingFailureException optimisticEntityLockException) {
            LOGGER.error("Drop from stock optimistic lock exception.Details: ->{}", optimisticEntityLockException.getLocalizedMessage());
        } catch (Exception e) {
            LOGGER.error("Drop from stock general exception.Details: ->{}", e.getLocalizedMessage());
            kafkaTemplate.send(this.failTopic, orderTopic);
        }
    }

    private void dropFromStock(OrderTopicDto orderTopicDto) {
        LOGGER.debug("Drop Stock Method: ");
        ProductDto productDto = super.read(orderTopicDto.getProductId());
        if ((productDto.getStock() - orderTopicDto.getQuantity()) < 0) {
            throw new ServiceException(EnumExceptionMessages.OUT_OF_STOCK);
        }
        updateProductStock(orderTopicDto.getProductId(), productDto.getStock() - orderTopicDto.getQuantity());
    }

}
