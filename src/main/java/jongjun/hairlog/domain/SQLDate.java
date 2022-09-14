package jongjun.hairlog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * SQLDate는 데이터를 DB에 저장할 때 생성, 수정, 삭제 시점이 필요하기 때문에 존재한다.
 */
@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SQLDate {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public SQLDate (LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * 추후 수정시점 업데이트시 사용
     * @param updatedAt
     */
    public void updateUpdateAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 추후 삭제시점 업데이트시 사용
     * @param deletedAt
     */
    public void updateDeleteAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
