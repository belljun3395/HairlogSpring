package jongjun.hairlog.repository;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.repository.repositoryInterface.RecordRepository;
import jongjun.hairlog.service.serviceInterface.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepository {
    private final EntityManager em;

    @Override
    public Long save(Record record) {
        if (record.getId() == null) {
            em.persist(record);
        }
        em.merge(record);
        return record.getId();
    }
    @Override
    public Boolean delete(Member member, Long recordId) {
        Member loginMember = em.find(Member.class, member.getId());
        Optional<Record> persistRecord = em.createQuery("select r from Record r where r.member = :member and r.id = :recordId", Record.class)
                                           .setParameter("member", loginMember)
                                           .setParameter("recordId", recordId)
                                           .getResultList()
                                           .stream()
                                           .findFirst();
        if(persistRecord.isEmpty()){
            throw new IllegalStateException("기록 정보가 잘못되었습니다.");
        }
        em.remove(persistRecord.get());
        return true;
    }

    @Override
    public Optional<Record> findById(Long memberId, Long id) {
        return em.createQuery("select r from Record r where r.id = :recordId and r.member.id = :memberId", Record.class)
                 .setParameter("recordId", id)
                 .setParameter("memberId", memberId)
                 .getResultList()
                 .stream()
                 .findAny();
    }

    @Override
    public List<Record> findRecords(Long memberId) {
        return em.createQuery("select r from Record r where r.member.id = :memberId ORDER BY r.recordDate", Record.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }



    // todo DiscriminatorColumn과 동일한 칼럼 이름 일때 어떻게 할까?
    //  => r.category 처리를 어떻게 할지 고민, Record에 category 추가 가능한가?
    @Override
    public List<Record> findByCategory(Long memberId, String category) {
        return null;
//        return em.createQuery("select r from Record r where r.category = :category and r.member.id = :memberId ORDER BY r.recordDate", Record.class)
//                 .setParameter("category", category)
//                 .setParameter("memberId", memberId)
//                 .getResultList();
    }

    // todo designer별 sorting 필요
    @Override
    public List<Record> findByDesigner(Long memberId, String designer) {
//        return em.createQuery("select r from Record r where r.designer.designerName = :designer and r.member.id = :memberId ORDER BY r.recordDate", Record.class)
//                 .setParameter("designer", designer)
//                 .setParameter("memberId", memberId)
//                 .getResultList();
        return null;

    }

}
