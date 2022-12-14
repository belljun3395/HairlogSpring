package jongjun.hairlog.domain.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.web.dto.get.GetRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "recordCategory")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Record {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate recordDate;

    private Integer recordCost;

    private String recordEtc;

    private String designerName;

    private Integer recordGrade;

    // todo DiscriminatorColumn과 동일한게 가능한가?
    private String category;

    @Embedded
    private SQLDate sqldate;

    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_id")
    private Designer designer;

    public GetRecordDTO toDTO() {
        return GetRecordDTO.builder()
                           .id(id)
                           .recordDate(recordDate)
                           .recordCost(recordCost)
                           .designerName(designerName)
                           .recordEtc(recordEtc)
                           .recordGrade(recordGrade)
                           .createdAt(sqldate.getCreatedAt())
                           .updatedAt(sqldate.getUpdatedAt())
                           .deletedAt(sqldate.getDeletedAt())
                           .build();
    }
}
