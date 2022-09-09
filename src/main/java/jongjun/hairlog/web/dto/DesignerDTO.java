package jongjun.hairlog.web.dto;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.designer.Designer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesignerDTO {

    private Long id;

    private String designerName;

    private String designerSalon;

    public Designer toEntity() {
        return Designer.builder()
                       .designerName(designerName)
                       .designerSalon(designerSalon)
                       .sqldate(new SQLDate(LocalDateTime.now(), LocalDateTime.now()))
                       .build();
    }

}
