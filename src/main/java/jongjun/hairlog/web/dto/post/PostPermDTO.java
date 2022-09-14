package jongjun.hairlog.web.dto.post;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.record.Perm;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.time.LocalDateTime;

import static jongjun.hairlog.web.dto.method.Transfer.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostPermDTO {

    @NotBlank
    private String permName;

    private String permTime;

    private String permHurt;

    public Perm toEntity(PostRecordDTO recordDTO) throws ParseException {
        return Perm.builder()
                   .recordDate(toLocalDateTime(recordDTO.getRecordDate()))
                   .recordCost(recordDTO.getRecordCost())
                   .designerName(recordDTO.getDesignerName())
                   .recordEtc(recordDTO.getRecordEtc())
                   .recordGrade(recordDTO.getRecordGrade())
                   .sqldate(new SQLDate(LocalDateTime.now(), LocalDateTime.now()))
                   .permName(permName)
                   .permTime(permTime)
                   .permHurt(permHurt)
                   .build();
    }
}
