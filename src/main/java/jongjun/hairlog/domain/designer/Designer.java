package jongjun.hairlog.domain.designer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.web.dto.get.GetDesignerDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Designer {

    @Id
    @GeneratedValue
    private Long id;

    private String designerName;

    private String designerSalon;

    private Boolean designerFav;

    @Embedded
    private SQLDate sqldate;

    // todo check JsonIgnore
    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Member member;

    public GetDesignerDTO toDTO() {
        return GetDesignerDTO.builder()
                             .id(id)
                             .designerName(designerName)
                             .designerSalon(designerSalon)
                             .designerFav(designerFav)
                             .sqldate(sqldate)
                             .build();
    }

}
