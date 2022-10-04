package jongjun.hairlog.service;

import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.repository.repositoryInterface.DesignerRepository;
import jongjun.hairlog.service.serviceInterface.DesignerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DesignerServiceImpl implements DesignerService {

    private final DesignerRepository designerRepository;
    private final EntityManager em;

    @Override
    @Transactional
    public Designer postDesigner(Long memberId, Designer designer) {
        Member member = em.find(Member.class, memberId);
        member.addDesigner(designer);
        return designerRepository.save(designer);
    }

    @Override
    public List<Designer> getDesigners(Long memberId) {
        return designerRepository.findByUserId(memberId);
    }

    @Override
    public List<Designer> getFaveDesigner(Long memberId) {
        return designerRepository.findByFav(memberId);
    }

    @Override
    @Transactional
    public Boolean deleteDesigner(Long memberId, Long designerId) {
        return designerRepository.delete(memberId, designerId);
    }
}
