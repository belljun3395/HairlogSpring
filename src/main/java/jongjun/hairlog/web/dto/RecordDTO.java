package jongjun.hairlog.web.dto;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {

    private Long id;

    private String recordDate;

    private Integer recordCost;

    private String designerName;

    private String recordEtc;

    private Integer recordGrade;

    private Record category;

    private SQLDate sqldate;

}
