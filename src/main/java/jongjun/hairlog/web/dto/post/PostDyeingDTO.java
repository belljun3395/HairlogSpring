package jongjun.hairlog.web.dto.post;


import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.record.Dyeing;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.time.LocalDateTime;

import static jongjun.hairlog.web.dto.method.Transfer.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDyeingDTO {

    @NotBlank
    private String dyeingColor;

    private String dyeingDecolorization;

    private String dyeingTime;

    private String dyeingHurt;


    public Dyeing toEntity(PostRecordDTO recordDTO) throws ParseException {
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
