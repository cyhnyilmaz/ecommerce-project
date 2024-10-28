package com.pttem.ecommerce.order.controller.order;

import com.pttem.ecommerce.order.constant.enums.EnumCrudMessages;
import com.pttem.ecommerce.order.constant.exceptions.ServiceException;
import com.pttem.ecommerce.order.dto.order.OrderDto;
import com.pttem.ecommerce.order.dto.response.ResponseTypeEnum;
import com.pttem.ecommerce.order.dto.response.RestResponse;
import com.pttem.ecommerce.order.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController  {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Query an Order", description = "Query an Order With Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully queried"),
            @ApiResponse(responseCode = "406", description = "Controlled Exception"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "read/{orderId}")
    public ResponseEntity<RestResponse<OrderDto>> read(@PathVariable Long orderId) {
       try {
           return new ResponseEntity<>(
                   new RestResponse<>(
                           orderService.read(orderId),
                           EnumCrudMessages.READ_TITLE.toString(),
                           EnumCrudMessages.READ_SUCCESS_MESSAGE.toString(),
                           ResponseTypeEnum.Success
                   ),
                   HttpStatus.OK
           );
       } catch (ServiceException e) {
           return new ResponseEntity<>(
                   new RestResponse<>(
                           EnumCrudMessages.READ_TITLE.toString(),
                           e.getMessage(),
                           ResponseTypeEnum.Error
                   ),
                   HttpStatus.NOT_ACCEPTABLE
           );
       }
    }

    @Operation(summary = "Place an Order", description = "Place an order with customer and product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully placed order"),
            @ApiResponse(responseCode = "406", description = "Controlled Exception"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "placeOrder")
    public ResponseEntity<RestResponse<OrderDto>> placeOrder(@RequestBody OrderDto newOrder) {
       try {
           return new ResponseEntity<>(
                   new RestResponse<>(
                           orderService.placeOrder(newOrder),
                           EnumCrudMessages.CREATE_TITLE.toString(),
                           EnumCrudMessages.CREATE_SUCCESS_MESSAGE.toString(),
                           ResponseTypeEnum.Success
                   ),
                   HttpStatus.OK
           );
       } catch (ServiceException e) {
           return new ResponseEntity<>(
                   new RestResponse<>(
                           EnumCrudMessages.CREATE_TITLE.toString(),
                           e.getMessage(),
                           ResponseTypeEnum.Error
                   ),
                   HttpStatus.NOT_ACCEPTABLE
           );
       }
    }

}
