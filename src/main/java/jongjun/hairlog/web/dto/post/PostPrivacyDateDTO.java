package jongjun.hairlog.web.dto.post;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostPrivacyDateDTO {

    private String userName;

    private LocalDate recentDate;

    private LocalDate nextDate;

}
