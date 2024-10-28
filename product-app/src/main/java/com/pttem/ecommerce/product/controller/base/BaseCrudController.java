
package com.pttem.ecommerce.product.controller.base;

import com.pttem.ecommerce.product.constant.enums.EnumCrudMessages;
import com.pttem.ecommerce.product.constant.exceptions.ServiceException;
import com.pttem.ecommerce.product.dto.base.BaseDto;
import com.pttem.ecommerce.product.dto.response.ResponseTypeEnum;
import com.pttem.ecommerce.product.dto.response.RestResponse;
import com.pttem.ecommerce.product.service.base.BaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseCrudController<D extends BaseDto> {
    private final BaseCrudService<D> baseCrudService;


    public BaseCrudController(BaseCrudService<D> baseCrudService) {
        this.baseCrudService = baseCrudService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<RestResponse<D>> create(@RequestBody D dto) {
        try {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            baseCrudService.create(dto),
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

    @PostMapping(value = "createAll")
    public ResponseEntity<RestResponse<List<D>>> createAll(@RequestBody List<D> dtoList) {
        try {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            baseCrudService.createAll(dtoList),
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

    @GetMapping(value = "read/{id}")
    public ResponseEntity<RestResponse<D>> read(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            baseCrudService.read(id),
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

    // @el (this: com.smartict.ats.controller.base.BaseCrudController)
//    @PreAuthorize("@checkPermission.hasPermission(authentication, this.READ)")
    @GetMapping(value = "read")
    public ResponseEntity<RestResponse<List<D>>> read() {
        try {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            baseCrudService.read(),
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
    // @el (this: com.smartict.ats.controller.base.BaseCrudController)
//    @PreAuthorize("@checkPermission.hasPermission(authentication, this.UPDATE)")
    @PutMapping(value = "update")
    public ResponseEntity<RestResponse<D>> update(@RequestBody D dto) {
        try {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            baseCrudService.update(dto),
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

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<RestResponse<D>> delete(@PathVariable Long id) {
        try {
            baseCrudService.delete(id);
            return new ResponseEntity<>(
                    new RestResponse<>(
                            EnumCrudMessages.DELETE_TITLE.toString(),
                            EnumCrudMessages.DELETE_SUCCESS_MESSAGE.toString(),
                            ResponseTypeEnum.Success
                    ),
                    HttpStatus.OK
            );
        } catch (ServiceException e) {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            EnumCrudMessages.DELETE_TITLE.toString(),
                            e.getMessage(),
                            ResponseTypeEnum.Error
                    ),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
    }

    // @el (this: com.smartict.ats.controller.base.BaseCrudController)
//    @PreAuthorize("@checkPermission.hasPermission(authentication, this.DELETE)")
    @DeleteMapping(value = "deleteAll")
    public ResponseEntity<RestResponse<List<D>>> deleteAll(@RequestBody List<Long> idList) {
        try {
            baseCrudService.deleteAll(idList);
            return new ResponseEntity<>(
                    new RestResponse<>(
                            EnumCrudMessages.DELETE_TITLE.toString(),
                            EnumCrudMessages.DELETE_SUCCESS_MESSAGE.toString(),
                            ResponseTypeEnum.Success
                    ),
                    HttpStatus.OK
            );
        } catch (ServiceException e) {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            EnumCrudMessages.DELETE_TITLE.toString(),
                            e.getMessage(),
                            ResponseTypeEnum.Error
                    ),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
    }
}
