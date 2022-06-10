package com.nhnacademy.account.entity;

import com.nhnacademy.account.vo.UserRequestVo;
import com.nhnacademy.account.vo.UserStatusVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "academy_users")
public class User {
    @Id
    @Column(name = "user_no")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer userNo;

    @Column(name = "user_id",unique = true)
    private String userId;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_status")
    private String status;

    @Builder
    public User(Integer userNo, String userId, String userPwd, String userName, String email, String status) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.email = email;
        this.status = status;
    }

    public User(UserRequestVo userRequestVo) {
        this.userId = userRequestVo.getId();
        this.userPwd = userRequestVo.getPwd();
        this.userName = userRequestVo.getName();
        this.email = userRequestVo.getEmail();
        this.status = userRequestVo.getStatus();
    }
}
