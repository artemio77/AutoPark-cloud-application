package com.gmail.derevets.artem.authservice.model.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionEntity {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HttpStatus status;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String details;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestUrl;

}
