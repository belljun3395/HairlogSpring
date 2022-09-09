package jongjun.hairlog.web.controller.designer;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.service.serviceInterface.DesignerService;
import jongjun.hairlog.service.serviceInterface.MemberService;
import jongjun.hairlog.web.dto.DesignerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static jongjun.hairlog.web.SessionConst.LoginMember;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DesignerController {

    private final EntityManager em;
    private final DesignerService designerService;
    private final MemberService memberService;

    @PostMapping("/designer")
    public Long postDesigner(@SessionAttribute(value = LoginMember) Member loginMember, DesignerDTO designerDTO) {
        Designer designer = designerDTO.toEntity();
        Member member = memberService.persistMember(loginMember);
        member.addDesigner(designer);
        return designerService.postDesigner(designer).getId();
    }

    @GetMapping("/designer")
    public List<Designer> getDesigner(@SessionAttribute(value = LoginMember) Member loginMember) {
        return designerService.getDesigners(loginMember.getId());
    }

    @GetMapping("/favDesigner")
    public List<Designer> getFavDesigner(@SessionAttribute(value = LoginMember) Member loginMember) {
        return designerService.getFaveDesigner(loginMember.getId());
    }

    @PostMapping("/designerDelete")
    public Boolean deleteDesigner(@SessionAttribute(value = LoginMember) Member loginMember, @RequestParam Long DesignerId) {
        return designerService.deleteDesigner(loginMember, DesignerId);
    }

    @PostMapping("/designerUpdate")
    public Designer updateDesigner(DesignerDTO designerDTO) {
        Designer designer = designerDTO.toEntity();
        return designerService.postDesigner(designer);
    }

}
