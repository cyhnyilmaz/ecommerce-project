
package com.pttem.ecommerce.product.controller.product;

import com.pttem.ecommerce.product.constant.enums.EnumCrudMessages;
import com.pttem.ecommerce.product.constant.exceptions.ServiceException;
import com.pttem.ecommerce.product.controller.base.BaseCrudController;
import com.pttem.ecommerce.product.dto.product.ProductDto;
import com.pttem.ecommerce.product.dto.response.ResponseTypeEnum;
import com.pttem.ecommerce.product.dto.response.RestResponse;
import com.pttem.ecommerce.product.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController extends BaseCrudController<ProductDto> {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }

    @Operation(summary = "Query a Product", description = "Query an Product With Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully queried"),
            @ApiResponse(responseCode = "406", description = "Controlled Exception"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "read/{productId}")
    public ResponseEntity<RestResponse<ProductDto>> read(@PathVariable Long productId) {
        try {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            productService.read(productId),
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

    @Operation(summary = "Updates a Product", description = "Updates a Product With Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully queried"),
            @ApiResponse(responseCode = "406", description = "Controlled Exception"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "updateProductStock/{productId}/{newStock}")
    public ResponseEntity<RestResponse> updateProductStock(@PathVariable(value = "productId") Long productId,@PathVariable(value = "newStock")  Long newStock) {
        try {
            productService.updateProductStock(productId,newStock);
            return new ResponseEntity<>(
                    new RestResponse<>(
                            EnumCrudMessages.UPDATE_TITLE.toString(),
                            EnumCrudMessages.UPDATE_SUCCESS_MESSAGE.toString(),
                            ResponseTypeEnum.Success
                    ),
                    HttpStatus.OK
            );
        } catch (ServiceException e) {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            EnumCrudMessages.UPDATE_TITLE.toString(),
                            e.getMessage(),
                            ResponseTypeEnum.Error
                    ),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
    }

    @Operation(summary = "Creates a Product", description = "Creates a Product ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "406", description = "Controlled Exception"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "createProduct")
    public ResponseEntity<RestResponse<ProductDto>> createProduct(@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            productService.create(productDto),
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
