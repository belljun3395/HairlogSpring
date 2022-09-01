package jongjun.hairlog.service;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.repository.MemberRepositoryImpl;
import jongjun.hairlog.service.serviceInterface.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final EntityManager em;
    private final MemberRepositoryImpl memberRepository;

    @Override
    @Transactional
    public Long join(Member member) {
        log.info("MemberService join method parameter = [member = {}]", member);
        validateDuplicateEmail(member.getEmail());
        Member joinMember = memberRepository.save(member);
        log.info("MemberService memberRepository.save result = [joinMember = {}]", joinMember);
        return joinMember.getId();
    }

    private void validateDuplicateEmail(String email) {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        if (!byEmail.isEmpty()) {
            log.info("MemberService join/validateDuplicateMember result fail = [byEmail = {}]", byEmail);
            throw new IllegalStateException("이미 존재하는 이메일 입니다");
        }
        log.info("MemberService join/validateDuplicateEmail result success");
    }

    @Override
    public Member login(String email, String password) {
        log.info("MemberService login method parameter = [email = {}][password = {}]", email, password);
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        if(byEmail.isEmpty()){
            log.info("로그인에 실패하였습니다. result fail = [cause = {}]", "아이디가 존재하지 않습니다.");
            throw new IllegalStateException("로그인에 실패하였습니다.");
        }
        return byEmail.filter(m -> m.getPassword().equals(password))
                .orElseThrow(() -> {
                    log.info("로그인에 실패하였습니다. result fail = [cause = {}]", "비밀번호가 일치하지 않습니다.");
                    throw new IllegalStateException("로그인에 실패하였습니다.");
                });
    }

    @Override
    public boolean checkPassword(Long id, String password) {
        log.info("MemberService checkPassword method parameter = [member id = {}][password ={}]", id, password);
        Optional<Member> byId = memberRepository.findById(id);
        if(byId.isEmpty()) {
            throw new IllegalStateException("다시 로그인 해주시기 바랍니다.");
        }
        if (byId.get().getPassword().equals(password)) {
            log.info("MemberService checkPassword method success");
            return true;
        }
        log.info("MemberService checkPassword method fail");
        return false;
    }

     // todo @DynamicUpdate 적용 고려하기
    @Override
    @Transactional
    public Member updateMember(Member member) {
        log.info("MemberService update method parameter = [member = {}]", member);
        Member updateMember = memberRepository.save(member);
        log.info("MemberService memberRepository.save result = [updateMember = {}]", updateMember);
        return updateMember;
    }

    @Override
    @Transactional
    public boolean deleteMember(Member member) {
        memberRepository.delete(member);
        return true;
    }

    @Override
    public Member persistMember(Member member) {
        return em.find(Member.class, member.getId());
    }

}
