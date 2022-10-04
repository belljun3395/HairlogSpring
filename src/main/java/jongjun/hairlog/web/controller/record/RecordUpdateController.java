package jongjun.hairlog.web.controller.record;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Cut;
import jongjun.hairlog.domain.record.Dyeing;
import jongjun.hairlog.domain.record.Perm;
import jongjun.hairlog.service.serviceInterface.MemberService;
import jongjun.hairlog.service.serviceInterface.RecordService;
import jongjun.hairlog.web.dto.post.PostCutDTO;
import jongjun.hairlog.web.dto.post.PostDyeingDTO;
import jongjun.hairlog.web.dto.post.PostPermDTO;
import jongjun.hairlog.web.dto.post.PostRecordDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.text.ParseException;

import static jongjun.hairlog.web.SessionConst.LoginMember;


@Slf4j
@RestController
@RequestMapping("/api/record/update")
@RequiredArgsConstructor
public class RecordUpdateController {

    // todo update 다시 구현

    private final RecordService recordService;
    private final MemberService memberService;

    // todo 추후 이미지처리도 해야함 현재는 이미지 빼고
    @PostMapping("/cut")
    public long postUpdateCutRecord(@SessionAttribute(value = LoginMember) Member loginMember, @Valid PostRecordDTO recordDTO, @Valid PostCutDTO cutDTO) throws ParseException {
        Cut cut = cutDTO.toEntity(recordDTO);
        return recordService.postRecord(loginMember.getId(), cut);
    }

    @PostMapping("/perm")
    public long postUpdatePermRecord(@SessionAttribute(value = LoginMember) Member loginMember, @Valid PostRecordDTO recordDTO, @Valid PostPermDTO permDTO) throws ParseException {
        Perm perm = permDTO.toEntity(recordDTO);
        return recordService.postRecord(loginMember.getId(), perm);
    }

    @PostMapping("dyeing")
    public long postUpdateDyeingRecord(@SessionAttribute(value = LoginMember) Member loginMember, @Valid PostRecordDTO recordDTO, @Valid PostDyeingDTO dyeingDTO) throws ParseException {
        Dyeing dyeing = dyeingDTO.toEntity(recordDTO);
        return recordService.postRecord(loginMember.getId(), dyeing);
    }

}
