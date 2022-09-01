package jongjun.hairlog.repository.repositoryInterface;

import jongjun.hairlog.domain.member.Member;

import java.util.List;
import java.util.Optional;

// note 조건 조회시 DB에 값이 없으면 어떻게 처리하는지 알아보기
// note Optional을 언제 사용하면 좋을까 알아보기
public interface MemberRepository {

    Member save(Member member);

    boolean delete(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    List<Member> findByName(String name);

}
