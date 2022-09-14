package jongjun.hairlog.domain.record;

import jongjun.hairlog.web.dto.get.GetRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("dyeing")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Dyeing extends Record {

    private String dyeingColor;

    private String dyeingDecolorization;

    private String dyeingTime;

    private String dyeingHurt;

    @Override
    public GetRecordDTO toDTO() {
        return GetRecordDTO.builder()
                           .id(super.getId())
                           .recordDate(super.getRecordDate())
                           .recordCost(super.getRecordCost())
                           .designerName(super.getDesignerName())
                           .recordEtc(super.getRecordEtc())
                           .recordGrade(super.getRecordGrade())
                           .createdAt(super.getSqldate()
                                           .getCreatedAt())
                           .updatedAt(super.getSqldate()
                                           .getUpdatedAt())
                           .deletedAt(super.getSqldate()
                                           .getDeletedAt())
                           .dyeingColor(dyeingColor)
                           .dyeingDecolorization(dyeingDecolorization)
                           .dyeingTime(dyeingTime)
                           .dyeingHurt(dyeingHurt)
                           .build();
    }
}
