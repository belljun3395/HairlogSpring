package jongjun.hairlog.domain.record;

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
    public String toString() {
        return "Perm{" +
                "permName='" + permName + '\'' +
                ", permTime='" + permTime + '\'' +
                ", permHurt='" + permHurt + '\'' +
                '}';
    }
}
