package jongjun.hairlog.domain.record;

import jongjun.hairlog.web.dto.get.GetRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("perm")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Perm extends Record {

    private String permName;

    private String permTime;

    private String permHurt;

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
                           .permHurt(permHurt)
                           .permName(permName)
                           .permTime(permTime)
                           .build();
    }
}
