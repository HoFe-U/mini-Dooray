package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.data.response.ResponseMember;
import com.nhnacademy.task.entity.Member;
import com.nhnacademy.task.repository.MemberRepository;
import com.nhnacademy.task.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public List<ResponseMember> getMembersNotInProject(Integer projectNo) {

        return memberRepository.findMembersNotInProject(projectNo).stream()
                .map(ResponseMember::new)
                .collect(Collectors.toList());
    }
    @Override
    public List<ResponseMember> getMemberInProject(Integer projectNo) {

        return memberRepository.findMemberInProject(projectNo).stream()
                .map(ResponseMember::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void registerMember(String id) {
        Member member = new Member(id);
        memberRepository.saveAndFlush(member);
    }
}
