package jongjun.hairlog.domain.designer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.member.Member;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private Member member;

    @Override
    public String toString() {
        return "Designer{" +
                "id=" + id +
                ", designerName='" + designerName + '\'' +
                ", designerSalon='" + designerSalon + '\'' +
                ", designerFav=" + designerFav +
                ", createdAt=" + sqldate.getCreatedAt() +
                ", updatedAt=" + sqldate.getUpdatedAt() +
                ", deletedAt=" + sqldate.getDeletedAt() +
                '}';
    }
}
