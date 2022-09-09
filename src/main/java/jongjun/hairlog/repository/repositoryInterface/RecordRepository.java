package jongjun.hairlog.repository.repositoryInterface;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;

import java.util.List;
import java.util.Optional;

public interface RecordRepository {

    Long save(Record record);

    Boolean delete(Member member, Long recordId);

    Optional<Record> findById(Long memberId, Long id);

    List<Record> findRecords(Long memberId);

    List<Record> findByCategory(Long memberId, String category);

    List<Record> findByDesigner(Long memberId, String designer);


}
