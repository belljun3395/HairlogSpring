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

    @NotNull(message = "기록 날짜를 입력하세요")
    private String recordDate;

    @NotNull(message = "기록 가격을 입력하세요")
    private Integer recordCost;

    @NotNull(message = "디자이너 이름을 입력하세요")
    private String designerName;

    private String recordEtc;

    @NotNull(message = "기록 만족도를 입력하세요")
    private Integer recordGrade;

//    @NotNull
//    private Record category;

    private SQLDate sqldate;

}
