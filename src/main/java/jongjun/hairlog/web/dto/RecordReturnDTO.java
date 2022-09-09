package jongjun.hairlog.web.dto;

import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecordReturnDTO {

    private Member user;

    private Designer designer;

    private Record record;

    private Record category;

    // todo img 추가하기
}

