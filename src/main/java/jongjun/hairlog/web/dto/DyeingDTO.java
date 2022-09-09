package jongjun.hairlog.web.dto;


import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.record.Dyeing;
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
public class DyeingDTO {

    private String dyeingColor;

    private String dyeingDecolorization;

    private String dyeingTime;

    private String dyeingHurt;


    public Dyeing toEntity(RecordDTO recordDTO) throws ParseException {
        return Dyeing.builder()
                     .recordDate(toLocalDateTime(recordDTO.getRecordDate()))
                     .recordCost(recordDTO.getRecordCost())
                     .designerName(recordDTO.getDesignerName())
                     .recordEtc(recordDTO.getRecordEtc())
                     .recordGrade(recordDTO.getRecordGrade())
                     .sqldate(new SQLDate(LocalDateTime.now(), LocalDateTime.now()))
                     .dyeingColor(dyeingColor)
                     .dyeingDecolorization(dyeingDecolorization)
                     .dyeingTime(dyeingTime)
                     .dyeingHurt(dyeingHurt)
                     .build();
    }
}
