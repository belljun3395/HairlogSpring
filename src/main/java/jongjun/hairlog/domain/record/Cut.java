package jongjun.hairlog.domain.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
    public String toString() {
        return "Cut{" +
                "cutName='" + cutName + '\'' +
                ", cutLength='" + cutLength + '\'' +
                '}';
    }
}
