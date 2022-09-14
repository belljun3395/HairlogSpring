package jongjun.hairlog.web.controller.designer;

import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.service.serviceInterface.DesignerService;
import jongjun.hairlog.service.serviceInterface.MemberService;
import jongjun.hairlog.web.dto.get.GetDesignerDTO;
import jongjun.hairlog.web.dto.post.PostDesignerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static jongjun.hairlog.web.SessionConst.LoginMember;
import static jongjun.hairlog.web.dto.method.Transfer.designersToListDTO;

@Slf4j
@RestController
@RequestMapping("/api/designer")
@RequiredArgsConstructor
public class DesignerController {

    private final DesignerService designerService;

    private final MemberService memberService;

    @PostMapping
    public Long postDesigner(@SessionAttribute(value = LoginMember) Member loginMember, @Validated PostDesignerDTO postDesignerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            throw new IllegalStateException(bindingResult.getFieldError()
                                                         .getDefaultMessage());
        }
        Designer designer = postDesignerDTO.toEntity();
        Member member = memberService.persistMember(loginMember);
        member.addDesigner(designer);
        return designerService.postDesigner(designer)
                              .getId();
    }

    @PostMapping("/update")
    public Long updateDesigner(@Validated PostDesignerDTO postDesignerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            throw new IllegalStateException(bindingResult.getFieldError()
                                                         .getDefaultMessage());
        }
        Designer designer = postDesignerDTO.toEntity();
        return designerService.postDesigner(designer)
                              .getId();
    }

    @PostMapping("/delete")
    public Boolean deleteDesigner(@SessionAttribute(value = LoginMember) Member loginMember, @RequestParam Long DesignerId) {
        return designerService.deleteDesigner(loginMember.getId(), DesignerId);
    }

    @GetMapping
    public List<GetDesignerDTO> getDesigner(@SessionAttribute(value = LoginMember) Member loginMember) {
        List<Designer> designers = designerService.getDesigners(loginMember.getId());
        ArrayList<GetDesignerDTO> getDesignerDTOS = designersToListDTO(designers);
        return getDesignerDTOS;
    }

    @GetMapping("/fav")
    public List<GetDesignerDTO> getFavDesigner(@SessionAttribute(value = LoginMember) Member loginMember) {
        List<Designer> faveDesigners = designerService.getFaveDesigner(loginMember.getId());
        ArrayList<GetDesignerDTO> getDesignerDTOS = designersToListDTO(faveDesigners);
        return getDesignerDTOS;
    }

}
