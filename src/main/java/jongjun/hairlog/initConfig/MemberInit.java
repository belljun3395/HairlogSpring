package jongjun.hairlog.initConfig;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.member.MemberSex;
import jongjun.hairlog.service.serviceInterface.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@RequiredArgsConstructor
public class MemberInit {

    private final MemberService memberService;

    /**
     * 새로운 Member를 만들고 Member를 가입시킨다.
     */
    public void init() {
        Member member = new Member("nanakim@gmail.com", "1234", "나나김", MemberSex.m, 3L, new SQLDate(LocalDateTime.now(), LocalDateTime.now()));
        memberService.join(member);
    }

}
