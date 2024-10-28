package com.pttem.ecommerce.user.controller.user;

import com.pttem.ecommerce.user.constant.enums.EnumCrudMessages;
import com.pttem.ecommerce.user.constant.exceptions.ServiceException;
import com.pttem.ecommerce.user.dto.response.ResponseTypeEnum;
import com.pttem.ecommerce.user.dto.response.RestResponse;
import com.pttem.ecommerce.user.dto.user.UserDto;
import com.pttem.ecommerce.user.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Queries a User", description = "Queries a User With Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully queried"),
            @ApiResponse(responseCode = "406", description = "Controlled Exception"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "read/{userId}")
    public ResponseEntity<RestResponse<UserDto>> read(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            userService.read(userId),
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

    @Operation(summary = "Queries that user's existince", description = "Queries that user's existince")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully queried"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "isUserExist/{id}")
    public ResponseEntity<Boolean> isUserExist(@PathVariable Long id) {
        try {
            UserDto result = userService.read(id);


            return new ResponseEntity<>(
                    Objects.nonNull(result),
                    HttpStatus.OK
            );
        } catch (ServiceException e) {
            return new ResponseEntity<>(
                    false,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
