package jongjun.hairlog.service;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.repository.repositoryInterface.RecordRepository;
import jongjun.hairlog.service.serviceInterface.MemberService;
import jongjun.hairlog.service.serviceInterface.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final EntityManager em;

    @Override
    @Transactional
    public Long postRecord(Long memberId, Record cut) {
        log.info("RecordService postRecord method parameter = [record = {}]", cut);
        Member member = em.find(Member.class, memberId);
        member.addRecord(cut, cut.getDesigner());
        return recordRepository.save(cut);
    }

    @Override
    public List<Record> getRecords(Long memberId) {
        return recordRepository.findRecords(memberId);
    }


    @Override
    public Record getRecordById(Long memberId, Long recordId) {
        Optional<Record> byId = recordRepository.findById(memberId, recordId);
        if (byId.isEmpty()) {
            throw new IllegalStateException("기록이 없습니다.");
        }
        return byId.get();
    }

    @Override
    public List<Record> getRecordByCategory(Long memberId, String category) {
        return recordRepository.findByCategory(memberId, category);
    }

    @Override
    public List<Record> getRecordByDesigner(Long memberId, String designer) {
        return recordRepository.findByDesigner(memberId, designer);
    }

    @Override
    @Transactional
    public boolean deleteRecord(Member member, Long recordId) {
        return recordRepository.delete(member, recordId);
    }
}