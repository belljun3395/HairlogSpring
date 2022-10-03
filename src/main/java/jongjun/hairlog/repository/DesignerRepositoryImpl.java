package jongjun.hairlog.repository;

import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.repository.repositoryInterface.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DesignerRepositoryImpl implements DesignerRepository {

    private final EntityManager em;

    @Override
    public Designer save(Designer designer) {
        if (designer.getId() == null) {
            em.persist(designer);
        }
        em.merge(designer);
        return designer;
    }

    // todo member or memberId?
    @Override
    public Boolean delete(Long memberId, Long designerId) {
        Optional<Designer> persistDesigner = em.createQuery("select d from Designer d where d.member.id = :memberId and d.id = :designerId", Designer.class)
                                               .setParameter("memberId", memberId)
                                               .setParameter("designerId", designerId)
                                               .getResultList()
                                               .stream()
                                               .findFirst();
        if(persistDesigner.isEmpty()){
            throw new IllegalStateException("기록 정보가 잘못되었습니다.");
        }
        em.remove(persistDesigner.get());
        return true;
    }

    @Override
    public Designer findById(Long designerId) {
        return em.find(Designer.class, designerId);
    }

    @Override
    public List<Designer> findByUserId(Long memberId) {
        return em.createQuery("select d from Designer d where d.member.id = :memberId", Designer.class)
                                    .setParameter("memberId", memberId)
                                    .getResultList();
    }

    @Override
    public List<Designer> findByFav(Long memberId) {
        return em.createQuery("select d from Designer d where d.member.id = :memberId and d.designerFav = true", Designer.class)
                 .setParameter("memberId", memberId)
                 .getResultList();
    }

}
