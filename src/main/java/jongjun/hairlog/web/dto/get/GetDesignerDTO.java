package jongjun.hairlog.web.dto.get;

import jongjun.hairlog.domain.SQLDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDesignerDTO {

    @NotNull
    private Long id;

    private String designerName;

    private String designerSalon;

    private Boolean designerFav;

    @Embedded
    private SQLDate sqldate;

}
