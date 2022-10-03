package jongjun.hairlog.web.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaverLoginDTO {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private Integer expires_in;

    @Override
    public String toString() {
        return "NaverLoginDTO{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
