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
    private String userEmail;

    @NotBlank
    private String userPassword;

}
