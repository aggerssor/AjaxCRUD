package cafe.jjdev.AjaxCRUD.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cafe.jjdev.AjaxCRUD.service.MemberService;
import cafe.jjdev.AjaxCRUD.vo.Member;
 
@RestController
public class MemberController {
    @Autowired 
    private MemberService memberService;
    
    
    @GetMapping("/getMembers")
    public Map<String, Object> getMembers(@RequestParam(value="currentPage") int currentPage ){ //값이 없으면 첫페이지는 1페이지로 고정
	
		System.out.println("[currentPage MemberController ]"+currentPage);
		System.out.println("/getMembers 요청");
		Map<String, Object> map = memberService.selectGetMember(currentPage);
		
		return map;
	}
    
    //멤버추가
    @PostMapping("/addMember")
    public void addMember(Member member) {
        System.out.println("/addMember 요청");
        System.out.println("MemberController.addMember member : "+member);
        memberService.addMember(member);
    }
    
    //멤버삭제
    @PostMapping("/removeMember")
    public void removeMember(@RequestParam(value="ck[]") String[] ck) { // List<String> ck
         System.out.println("/removeMember 요청");
        System.out.println(ck);
        for(String id : ck) {
            Member member = new Member();
            member.setId(id);
            memberService.removeMember(ck);
        }
    }
    
    //멤버수정
    @PostMapping("/modifyMember")
    public void modifyMember(Member member) {
        System.out.println("/modifyMember 요청");
        System.out.println("MemberController.modifyMember member : "+member);
        memberService.modifyMember(member);
    }
}


