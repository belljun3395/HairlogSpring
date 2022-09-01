package jongjun.hairlog.domain.member;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.record.Record;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberSex sex;

    private Long cycle;

    @Embedded
    private SQLDate sqldate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> records;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Designer> designers;

    public void addRecord(Record record, Designer designer) {
        this.getRecords()
            .add(record);
        this.getDesigners()
            .add(designer);
        record.setMember(this);
    }

    public void addDesigner(Designer designer) {
        this.getDesigners()
            .add(designer);
        designer.setMember(this);
    }
    public Member(String email, String password, String name, MemberSex sex, Long cycle, SQLDate sqldate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.cycle = cycle;
        this.sqldate = sqldate;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", cycle=" + cycle +
                ", createdAt=" + sqldate.getCreatedAt() +
                ", updatedAt=" + sqldate.getUpdatedAt() +
                ", deletedAt=" + sqldate.getDeletedAt() +
                '}';
    }

}
