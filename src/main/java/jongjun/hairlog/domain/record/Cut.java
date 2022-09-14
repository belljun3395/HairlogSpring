package jongjun.hairlog.domain.record;

import jongjun.hairlog.web.dto.get.GetRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cut")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Cut extends Record {

    private String cutName;

    private String cutLength;

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
                           .cutLength(cutLength)
                           .cutName(cutName)
                           .build();
    }
}
