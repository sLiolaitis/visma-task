package com.simas.vismajavadevelopertask.service;

import com.simas.vismajavadevelopertask.model.Member;

import java.util.List;

public interface MemberService {
    Member addMember (Member member);
    List<Member> getAllMembers();
    Member getMemberById (long id);
}
