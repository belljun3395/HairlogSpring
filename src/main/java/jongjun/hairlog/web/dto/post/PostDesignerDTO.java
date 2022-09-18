package jongjun.hairlog.web.dto.post;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.designer.Designer;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDesignerDTO {

    private Long id;

    @NotBlank(message = "디자이너 이름을 입력하세요")
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
