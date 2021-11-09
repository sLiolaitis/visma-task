package com.simas.vismajavadevelopertask.service.implementation;

import com.google.gson.Gson;
import com.simas.vismajavadevelopertask.model.Member;
import com.simas.vismajavadevelopertask.service.MemberService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public Member addMember(Member member) {
        List<Member> memberList = null;
        BufferedReader reader = null;
        List<Member> newList = null;
        try {
            reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\members.json"));
            Gson gson = new Gson();
            Member[] membersArray = gson.fromJson(reader, Member[].class);
            if (membersArray != null) {
                memberList = Arrays.asList(membersArray);
                newList = new ArrayList<>();
                newList.add(member);
                newList.addAll(memberList);
                reader.close();
            } else {
                newList = new ArrayList<>();
                newList.add(member);
            }
            try {
                FileWriter fileWriter = new FileWriter("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\members.json");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                Set<Member> memberSet = new HashSet<>(newList);
                newList = new ArrayList<>(memberSet);
                bufferedWriter.write(new Gson().toJson(newList));
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Exception while writing");
            }
        } catch (Exception e) {
            System.out.println("Exception while reading");
        }
        return member;
    }


    @Override
    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        try {
            Reader reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\members.json"));
            Gson gson = new Gson();
            Member[] array = gson.fromJson(reader, Member[].class);
            list = Arrays.asList(array);
        } catch (Exception e) {
            System.out.println("Error while reading");
        }

        return list;
    }

    @Override
    public Member getMemberById(long id) {
        Reader reader = null;
        Member[] membersArray = null;
        List<Member> membersList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("D:\\Visma-Java-Developer-Task\\src\\main\\resources\\members.json"));
            Gson gson = new Gson();
            membersArray = gson.fromJson(reader, Member[].class);
            reader.close();
            membersList = Arrays.asList(membersArray);
        } catch (Exception e){
            System.out.println("Reading exception");
        }

        for(Member m : membersList){
            if(m.getMemberId()==id){
                return m;
            }
        }

        return null;

    }
}
