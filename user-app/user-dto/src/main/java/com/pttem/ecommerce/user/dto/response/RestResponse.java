
package com.pttem.ecommerce.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {
    private T data;
    private String titleLanguageKey;
    private String title;
    private String messageLanguageKey;
    private String message;
    private ResponseTypeEnum type;


    public RestResponse(T data,String title, String message, ResponseTypeEnum type) {
        this.data = data;
        this.title = title;
        this.message = message;
        this.type = type;
    }

    public RestResponse(String title, String message, ResponseTypeEnum type) {
        this.title = title;
        this.message = message;
        this.type = type;
    }

//    public RestResponse(String messageLanguageKey, String message, ResponseTypeEnum type) {
//        this.title = "";
//        this.titleLanguageKey = "";
//        this.message = message;
//        this.messageLanguageKey = messageLanguageKey;
//        this.type = type;
//    }

    public RestResponse(String titleLanguageKey, String title, String messageLanguageKey, String message, ResponseTypeEnum type) {
        this.title = title;
        this.titleLanguageKey = titleLanguageKey;
        this.message = message;
        this.messageLanguageKey = messageLanguageKey;
        this.type = type;
    }

}
