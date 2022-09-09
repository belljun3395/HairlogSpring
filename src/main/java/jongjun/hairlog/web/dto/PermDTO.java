package jongjun.hairlog.web.dto;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.record.Perm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.time.LocalDateTime;

import static jongjun.hairlog.web.dto.method.Transfer.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermDTO {

    private String permName;

    private String permTime;

    private String permHurt;

    public Perm toEntity(RecordDTO recordDTO) throws ParseException {
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
