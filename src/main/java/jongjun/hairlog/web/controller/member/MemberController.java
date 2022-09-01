package jongjun.hairlog.web.controller.member;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.repository.repositoryInterface.RecordRepository;
import jongjun.hairlog.service.serviceInterface.MemberService;
import jongjun.hairlog.web.dto.LoginDTO;
import jongjun.hairlog.web.dto.MemberDTO;
import jongjun.hairlog.web.dto.PrivacyDateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static jongjun.hairlog.web.SessionConst.LoginMember;

// todo bindingResult 알아보기
// todo @Valiated 알아보기
// todo IllegalStateException 알아보기
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RecordRepository recordRepository;
    private final EntityManager em;

    @PostMapping("/join")
    public Long join(@Validated MemberDTO memberDTO) {
//        memberDTO.setSqldate(new SQLDate(LocalDateTime.now(), LocalDateTime.now()));
        Member member = memberDTO.toEntity();
        return memberService.join(member);
    }

    @PostMapping("/authenticate")
    public void login(@Validated LoginDTO loginDTO, HttpServletRequest req) {
        Member loginMember = memberService.login(loginDTO.getUserEmail(), loginDTO.getUserPassword());
        if (loginMember == null) {
            throw new IllegalStateException("로그인에 실패하였습니다");
        }

        HttpSession session = req.getSession();
        session.setAttribute(LoginMember, loginMember);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @PostMapping("/checkPassword")
    public boolean checkPassword(@SessionAttribute(LoginMember) Member loginMember, @RequestParam String userPassword) {
        return memberService.checkPassword(loginMember.getId(), userPassword);
    }

    @PostMapping("/joinDelete")
    public boolean deleteMember(HttpServletRequest req, @SessionAttribute(LoginMember) Member loginMember) {
        boolean deleteMember = memberService.deleteMember(loginMember);
        logout(req);
        return deleteMember;
    }

    @PostMapping("/privacyUpdate/user")
    public void updatePrivacy(HttpServletRequest req, @SessionAttribute(LoginMember) Member loginMember, MemberDTO updateMemberRecord) {
        MemberDTO memberDTO = new MemberDTO(loginMember, updateMemberRecord, new SQLDate(loginMember.getSqldate()
                                                                                                    .getCreatedAt(), LocalDateTime.now()));
        Member memberEntity = memberDTO.toEntity();
        Member updateMember = memberService.updateMember(memberEntity);

        HttpSession session = req.getSession();
        session.setAttribute(LoginMember, updateMember);
    }

    @GetMapping("/privacy/user")
    public PrivacyDateDTO privacyDate(@SessionAttribute(LoginMember) Member loginMember) {
        Optional<Record> latestRecord = recordRepository.findRecords(loginMember.getId())
                                                        .stream()
                                                        .findFirst();
        // todo 기록이 없을 때 처리
        if (latestRecord.isEmpty()) {
            throw new IllegalStateException("기록이 없습니다.");
        }

        Record record = latestRecord.get();
        LocalDate recentDate = record.getRecordDate();
        LocalDate nextDate = recentDate.plusDays(loginMember.getCycle());

        return new PrivacyDateDTO(loginMember.getName(), recentDate, nextDate);
    }

}
