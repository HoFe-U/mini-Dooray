package com.nhnacademy.account.data.response;

import com.nhnacademy.account.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIdResponse {
    private String id;

    public UserIdResponse(User user) {
        this.id = user.getUserId();
    }
}
