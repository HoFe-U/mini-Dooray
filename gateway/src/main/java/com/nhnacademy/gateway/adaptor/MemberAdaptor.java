package com.nhnacademy.gateway.adaptor;

import com.nhnacademy.gateway.data.response.MemberResponse;

public interface MemberAdaptor {
    void joinMember(String id, String name, String pwd, String email);

    MemberResponse findMemberId(String id);

    MemberResponse findMemberEmail(String email);

    void fixMemberInfo(String id,String pwd,String name,String email,String status);

    void fixMemberStatus(String status);

}
