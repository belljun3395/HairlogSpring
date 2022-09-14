package jongjun.hairlog.web.controller.member;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.repository.repositoryInterface.RecordRepository;
import jongjun.hairlog.service.serviceInterface.MemberService;
import jongjun.hairlog.web.dto.post.LoginDTO;
import jongjun.hairlog.web.dto.post.PostMemberDTO;
import jongjun.hairlog.web.dto.post.PostPrivacyDateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static jongjun.hairlog.web.SessionConst.LoginMember;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RecordRepository recordRepository;

    @PostMapping("/join")
    public Long join(@Validated PostMemberDTO postMemberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            throw new IllegalStateException(bindingResult.getFieldError()
                                                         .getDefaultMessage());
        }
        Member member = postMemberDTO.toEntity();
        return memberService.join(member);
    }

    @PostMapping("/authenticate")
    public void login(HttpServletRequest req, @Validated LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            throw new IllegalStateException(bindingResult.getFieldError()
                                                         .getDefaultMessage());
        }

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

    @PostMapping("/delete")
    public boolean deleteMember(HttpServletRequest req, @SessionAttribute(LoginMember) Member loginMember) {
        boolean deleteMember = memberService.deleteMember(loginMember);
        logout(req);
        return deleteMember;
    }

    @PostMapping("/update")
    public void updatePrivacy(HttpServletRequest req, @SessionAttribute(LoginMember) Member loginMember, PostMemberDTO updateMemberRecord, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            throw new IllegalStateException(bindingResult.getFieldError()
                                                         .getDefaultMessage());
        }
        PostMemberDTO postMemberDTO = new PostMemberDTO(loginMember, updateMemberRecord, new SQLDate(loginMember.getSqldate()
                                                                                                                .getCreatedAt(), LocalDateTime.now()));
        Member memberEntity = postMemberDTO.toEntity();
        Member updateMember = memberService.updateMember(memberEntity);

        HttpSession session = req.getSession();
        session.setAttribute(LoginMember, updateMember);
    }

    @GetMapping("/memberInfo")
    public PostPrivacyDateDTO privacyDate(@SessionAttribute(LoginMember) Member loginMember) {
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

        return new PostPrivacyDateDTO(loginMember.getName(), recentDate, nextDate);
    }

}

