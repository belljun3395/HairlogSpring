package jongjun.hairlog.web.dto;

import jongjun.hairlog.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivacyDateDTO {

    private String userName;

    private LocalDate recentDate;

    private LocalDate nextDate;

}
