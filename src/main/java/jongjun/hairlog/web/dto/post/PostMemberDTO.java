package jongjun.hairlog.web.dto.post;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.member.MemberSex;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMemberDTO {

    private Long id;

    @NotBlank
    private String userName;

    @NotNull
    private MemberSex userSex;

    @NotNull
    private Long userCycle;

    @Email
    private String userEmail;

    @NotBlank
    private String userPassword;

    private SQLDate sqldate;

    public PostMemberDTO(Member member, PostMemberDTO postMemberDTO, SQLDate sqldate) {
        this.id = member.getId();
        this.userName = postMemberDTO.getUserName();
        this.userSex = postMemberDTO.getUserSex();
        this.userCycle = postMemberDTO.getUserCycle();
        this.userEmail = postMemberDTO.getUserEmail();
        this.userPassword = postMemberDTO.getUserPassword();
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
