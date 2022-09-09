package jongjun.hairlog.web.controller.record;

import jongjun.hairlog.domain.SQLDate;
import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Cut;
import jongjun.hairlog.domain.record.Dyeing;
import jongjun.hairlog.domain.record.Perm;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.service.serviceInterface.MemberService;
import jongjun.hairlog.service.serviceInterface.RecordService;
import jongjun.hairlog.web.dto.DyeingDTO;
import jongjun.hairlog.web.dto.PermDTO;
import jongjun.hairlog.web.dto.CutDTO;
import jongjun.hairlog.web.dto.RecordDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import static jongjun.hairlog.web.SessionConst.LoginMember;

@Slf4j
@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
public class RecordCategoryController {

    private final RecordService recordService;
    private final MemberService memberService;


    // todo 추후 이미지처리도 해야함 현재는 이미지 빼고
    @PostMapping("/cut")
    public long postCutRecord(@SessionAttribute(value = LoginMember) Member loginMember, RecordDTO recordDTO, CutDTO cutDTO) throws ParseException {
        Cut cut = cutDTO.toEntity(recordDTO);
        Member member = memberService.persistMember(loginMember);
        member.addRecord(cut, cut.getDesigner());
        return recordService.postRecord(cut);
    }

    @PostMapping("/perm")
    public long postPermRecord(@SessionAttribute(value = LoginMember) Member loginMember, RecordDTO recordDTO, PermDTO permDTO) throws ParseException {
        Perm perm = permDTO.toEntity(recordDTO);
        Member member = memberService.persistMember(loginMember);
        member.addRecord(perm, perm.getDesigner());
        return recordService.postRecord(perm);
    }

    @PostMapping("dyeing")
    public long postDyeingRecord(@SessionAttribute(value = LoginMember) Member loginMember, RecordDTO recordDTO, DyeingDTO dyeingDTO) throws ParseException {
        Dyeing dyeing = dyeingDTO.toEntity(recordDTO);
        Member member = memberService.persistMember(loginMember);
        member.addRecord(dyeing, dyeing.getDesigner());
        return recordService.postRecord(dyeing);
    }

}