package fr.org.aelion.atos2024.cyber.models.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class ApiErrorResponse {
    private Integer code;
    private String msg;
    private Exception exception;

    // No setter to emulate final behaviour
    @Value("${debug:false}")
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private boolean debugMode; // TODO: /!\ Boolean is not working here

    public ApiErrorResponse(Integer code, String msg, Exception exception) {
        this.code = code;
        this.msg = msg;
        if(this.debugMode) { // if DEBUG ACTIVE
            this.exception = exception;
        }
    }
}