package jongjun.hairlog.web.dto.post;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.record.Record;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRecordDTO {

    private Long id;

    @NotNull
    private String recordDate;

    @NotBlank
    private Integer recordCost;

    @NotBlank
    private String designerName;

    private String recordEtc;

    @NotNull
    private Integer recordGrade;

    @NotNull
    private Record category;

    private SQLDate sqldate;

}
