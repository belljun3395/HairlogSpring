package jongjun.hairlog.web.dto;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.record.Cut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.time.LocalDateTime;

import static jongjun.hairlog.web.dto.method.Transfer.toLocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CutDTO {

    private String cutName;

    private String cutLength;

    public Cut toEntity(RecordDTO recordDTO) throws ParseException {
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