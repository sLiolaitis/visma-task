package com.simas.vismajavadevelopertask.service.implementation;

import com.google.gson.Gson;
import com.simas.vismajavadevelopertask.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberServiceImplTest {

    @Test
    @DisplayName("Testing array of Members size after adding a member")
    public void addMemberTest(){
        MemberServiceImpl memberService = new MemberServiceImpl();
        List<Member> members = getAllMembers();

        Member member = new Member(256874, "Kazimieras","Petraitis" );
        int sizeBeforeAddingMember = getAllMembers().size();
        memberService.addMember(member);
        int sizeAfterAddingMember = getAllMembers().size();

        Assertions.assertEquals((sizeBeforeAddingMember+1),sizeAfterAddingMember);

        List<Member> newList = new ArrayList<>();
        for(Member m : members){
            if(m.getMemberId()!=256874) newList.add(m);
        }

        try{
            Writer writer = new BufferedWriter(new FileWriter("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\members.json",false));
            writer.write(new Gson().toJson(newList));
            writer.close();
        } catch (Exception e){
            System.out.println("Error while writing");
        }

    }

    @Test
    @DisplayName("Testing if size of all members Array is correct")
    public void getAllMembersTest(){
        MemberServiceImpl memberService = new MemberServiceImpl();
        int size1 = getAllMembers().size();
        int size2 = memberService.getAllMembers().size();
        Assertions.assertEquals(size1, size2);
    }

    @Test
    @DisplayName("Testing if member found by ID is correct")
    public void getMemberByIdTest(){
        MemberServiceImpl memberService = new MemberServiceImpl();
        List<Member> membersList = memberService.getAllMembers();
        long firstMemberId = membersList.get(0).getMemberId();
        String firstMemberName = membersList.get(0).getName();
        String firstMemberSurname = membersList.get(0).getSurname();
        Member foundMember = memberService.getMemberById(firstMemberId);
        String foundMemberName = foundMember.getName();
        String foundMemberSurname = foundMember.getSurname();

        Assertions.assertEquals(firstMemberName,foundMemberName);
        Assertions.assertEquals(firstMemberSurname,foundMemberSurname);

    }

    public List<Member> getAllMembers (){
        List<Member> members = new ArrayList<>();
        try{
            Reader reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\members.json"));
            members = Arrays.asList(new Gson().fromJson(reader, Member[].class));
            reader.close();
        } catch (Exception e){
            System.out.println("Error while reading");
        }
        return members;
    }

}
