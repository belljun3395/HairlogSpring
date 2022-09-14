package jongjun.hairlog.web.dto.get;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetRecordDTO {

    private Long id;

    private LocalDate recordDate;

    private Integer recordCost;

    private String recordEtc;

    private String designerName;

    private Integer recordGrade;

    private String category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String cutName;

    private String cutLength;

    private String dyeingColor;

    private String dyeingDecolorization;

    private String dyeingTime;

    private String dyeingHurt;

    private String permName;

    private String permTime;

    private String permHurt;

    // todo img 추가하기
}

