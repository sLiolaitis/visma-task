package com.simas.vismajavadevelopertask.controller;

import com.simas.vismajavadevelopertask.model.Member;
import com.simas.vismajavadevelopertask.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visma-task/members")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/addMember")
    public ResponseEntity<Member> addMember (@RequestBody Member member){
        return new ResponseEntity<Member>(memberService.addMember(member), HttpStatus.OK);
    }

    @GetMapping("/getAllMembers")
    public ResponseEntity<List<Member>> getAllMembers (){
        return new ResponseEntity<List<Member>>(memberService.getAllMembers(), HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById (@PathVariable long id){
        return new ResponseEntity<Member>(memberService.getMemberById(id), HttpStatus.OK);
    }

}
