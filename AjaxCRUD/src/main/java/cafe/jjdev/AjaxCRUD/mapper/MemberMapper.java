package cafe.jjdev.AjaxCRUD.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.AjaxCRUD.vo.Member;

@Mapper
public interface MemberMapper {
	//회원리스트
	public List<Member> selectMemberList(Map<String , Integer> Map);
	//회원추가
	public int insertMember(Member member);
	//회원삭제
	public int deleteMember(Member member);
	//회원수정
	public int updateMember(Member member);
	//회원리스트 페이징을 위한
		
	// 라스트 페이지를 구하기 위해 전체 행의 수를 가져오는 메소드
	public int countMember();
}
