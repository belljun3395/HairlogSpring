package jongjun.hairlog.domain.record;

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
    public String toString() {
        return "Dyeing{" +
                "dyeingColor='" + dyeingColor + '\'' +
                ", dyeingDecolorization='" + dyeingDecolorization + '\'' +
                ", dyeingTime='" + dyeingTime + '\'' +
                ", dyeingHurt='" + dyeingHurt + '\'' +
                '}';
    }
}
