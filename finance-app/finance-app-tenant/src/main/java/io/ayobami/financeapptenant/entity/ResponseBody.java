package io.ayobami.financeapptenant.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseBody {

    private int statusCode;
    private String message;
    private Object data;
}
