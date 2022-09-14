package jongjun.hairlog.web.dto.post;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.record.Cut;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.time.LocalDateTime;

import static jongjun.hairlog.web.dto.method.Transfer.toLocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCutDTO {

    @NotBlank
    private String cutName;

    private String cutLength;

    public Cut toEntity(PostRecordDTO recordDTO) throws ParseException {
        return Cut.builder()
                  .recordDate(toLocalDateTime(recordDTO.getRecordDate()))
                  .recordCost(recordDTO.getRecordCost())
                  .designerName(recordDTO.getDesignerName())
                  .recordEtc(recordDTO.getRecordEtc())
                  .recordGrade(recordDTO.getRecordGrade())
                  .sqldate(new SQLDate(LocalDateTime.now(), LocalDateTime.now()))
                  .cutName(cutName)
                  .cutLength(cutLength)
                  .build();
    }

}