package jongjun.hairlog.service.serviceInterface;

import jongjun.hairlog.domain.member.Member;

public interface MemberService {

    Long join(Member member);

    Member login(String email, String password);

    boolean checkPassword(Long id, String password);

    Member updateMember(Member member);

    boolean deleteMember(Member member);

    Member persistMember(Member member);

}
