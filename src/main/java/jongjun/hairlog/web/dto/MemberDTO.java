package jongjun.hairlog.web.dto;

import com.sun.istack.NotNull;
import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.member.MemberSex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;

    @NotNull
    private String userName;

    @NotNull
    private MemberSex userSex;

    @NotNull
    private Long userCycle;

    @NotNull
    private String userEmail;

    @NotNull
    private String userPassword;

    private SQLDate sqldate;

    public MemberDTO(Member member, MemberDTO memberDTO, SQLDate sqldate) {
        this.id = member.getId();
        this.userName = memberDTO.getUserName();
        this.userSex = memberDTO.getUserSex();
        this.userCycle = memberDTO.getUserCycle();
        this.userEmail = memberDTO.getUserEmail();
        this.userPassword = memberDTO.getUserPassword();
        this.sqldate = sqldate;
    }

    public Member toEntity() {
        return Member.builder()
                     .id(id)
                     .name(userName)
                     .email(userEmail)
                     .password(userPassword)
                     .cycle(userCycle)
                     .sex(userSex)
                     .sqldate(new SQLDate(LocalDateTime.now(), LocalDateTime.now()))
                     .build();
    }

}
