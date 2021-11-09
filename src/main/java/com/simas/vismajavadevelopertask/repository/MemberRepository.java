package com.simas.vismajavadevelopertask.repository;

import com.simas.vismajavadevelopertask.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
