package jongjun.hairlog.repository;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.repository.repositoryInterface.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public Member save(Member member) {
        if (member.getId() == null) {
            em.persist(member);
        }
        em.merge(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        try {
            return Optional.ofNullable(em.find(Member.class, id));
        } catch (NoResultException e) {
            return Optional.ofNullable(null);
        }
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny();
    }

    @Override
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    // todo 추후 deleted_at 입력 방식으로 수정
    @Override
    public boolean delete(Member member) {
        Member persistMember = em.find(Member.class, member.getId());
//        Optional<Member> byId = findById(member.getId());
//        Member persistMember = byId.get();
        em.remove(persistMember);
        return true;
    }
}
