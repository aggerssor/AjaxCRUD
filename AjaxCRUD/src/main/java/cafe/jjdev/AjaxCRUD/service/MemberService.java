package cafe.jjdev.AjaxCRUD.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.AjaxCRUD.mapper.MemberMapper;
import cafe.jjdev.AjaxCRUD.vo.Member;


@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	
	// 페이징 작업
	public Map<String, Object> selectGetMember(int currentPage){
		//페이지당 갯수
		final int rowPerPage = 10;
		//페이지
		int beginRow = (currentPage-1)*rowPerPage;
		// 1페이지 일 때 10개를 보여줄 것 
		// LIMIT 0, 10일때 처음부터 10개를 보여줌
		// 2페이지 일 때 10개를 보여주려면
		// LIMIT 10, 10
		// 3페이지 일 때 10개를 보여주려면
		// LIMIT 20, 10
		// 4페이지 일 때 10개를 보여주려면
		// LIMIT 30, 10
		
		
		//  마지막 페이지 구하기
		// 01단계 전체 행 구하기
		int totalCount = memberMapper.countMember();
		// 02단계 마지막 페이지 구하기
		int lastPage = 0;
		
		if(totalCount%rowPerPage == 0) {
			lastPage = totalCount/rowPerPage;
		}else {
			lastPage = totalCount/rowPerPage+1;
		}
		
		// 페이지에 해당하는 목록을 조회하기 위한 map
		Map<String , Integer> map = new HashMap<String , Integer>();	
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		
		// 리턴 할 resultMap
		Map<String , Object> resultMap = new HashMap<String , Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("list", memberMapper.selectMemberList(map));
		
		return resultMap;
	}

	//맴버추가
	public void addMember(Member member) {
		System.out.println("MemberService.addMember member : "+member);
		memberMapper.insertMember(member);
	}
	
	//멤버 삭제
	public void removeMember(String[] ck) {
		System.out.println("/removeMember 요청");
		System.out.println("MemberService.removeMember ck : "+ck);
		System.out.println("MemberService.addMember ck[0] : "+ck[0]);
		//System.out.println("MemberController.removeMember id : "+ck.length); // 이 작업은 서비스 쪽에서 이루어져야 맞다
		for(String id : ck) {
			Member member = new Member();
			member.setId(id);
			memberMapper.deleteMember(member);
		}
	}
	
	// 멤버 수정
		public void modifyMember(Member member) {
			System.out.println("MemberService.modifyMember member : "+member);
			memberMapper.updateMember(member);
		}

		public Map<String, Object> getMemberList(int currentPage) {
			// 호출된 메서드명과 입력값 확인
			System.out.println("MemberService.getMemberList");
			System.out.println("MemberService.getMemberList currentPage : " + currentPage);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			return map;
		}

}
