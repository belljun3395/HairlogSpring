package jongjun.hairlog.service.serviceInterface;

import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;

import java.util.List;

public interface DesignerService {

    Designer postDesigner(Designer designer);

    List<Designer> getDesigners(Long memberId);

    List<Designer> getFaveDesigner(Long memberId);

    Boolean deleteDesigner(Long memberId, Long designerId);

}
