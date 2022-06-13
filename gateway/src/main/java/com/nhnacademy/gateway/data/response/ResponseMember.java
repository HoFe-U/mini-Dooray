package com.nhnacademy.gateway.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ResponseMember {
    @NotNull
    private Integer memberNo;
    @NotNull
    private String id;

    public ResponseMember(String id) {
        this.id = id;
    }
}
