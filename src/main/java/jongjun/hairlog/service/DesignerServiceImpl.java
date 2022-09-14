package jongjun.hairlog.service;

import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.repository.repositoryInterface.DesignerRepository;
import jongjun.hairlog.service.serviceInterface.DesignerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerServiceImpl implements DesignerService {

    private final DesignerRepository designerRepository;

    @Override
    @Transactional
    public Designer postDesigner(Designer designer) {
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
