package jongjun.hairlog.service.serviceInterface;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;

import java.util.List;

public interface RecordService {

    Long postRecord(Record record);

    List<Record> getRecords(Long memberId);

    Record getRecordById(Long memberId, Long recordId);

    List<Record> getRecordByCategory(Long memberId, String category);

    List<Record> getRecordByDesigner(Long memberId, String designer);

    boolean deleteRecord(Member member, Long recordId);

}
