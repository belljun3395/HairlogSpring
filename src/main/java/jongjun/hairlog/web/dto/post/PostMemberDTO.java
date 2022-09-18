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

    @NotBlank(message = "유저 이름을 입력하세요")
    private String userName;

    @NotNull(message = "성별을 입력하세요")
    private MemberSex userSex;

    @NotNull(message = "주기를 입력하세요")
    private Long userCycle;

    @Email
    @NotNull(message = "email을 입력하세요")
    private String userEmail;

    @NotBlank(message = "비밀번호를 입력하세요")
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
