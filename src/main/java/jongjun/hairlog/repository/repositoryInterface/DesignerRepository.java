package jongjun.hairlog.repository.repositoryInterface;

import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;

import java.util.List;

public interface DesignerRepository {

    Designer save(Designer designer);

    Boolean delete(Member member, Long designerId);

    Designer findById(Long designerId);

    List<Designer> findByUserId(Long memberId);

    List<Designer> findByFav(Long memberId);

}
