package jongjun.hairlog.web.dto.post;


import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @Email
    @NotBlank(message = "이메일을 입력하세요")
    private String userEmail;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String userPassword;

}
