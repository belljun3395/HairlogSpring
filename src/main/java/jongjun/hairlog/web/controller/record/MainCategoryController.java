package jongjun.hairlog.web.controller.record;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.service.serviceInterface.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static jongjun.hairlog.web.SessionConst.LoginMember;

@Slf4j
@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainCategoryController {

    private final RecordService recordService;


    @GetMapping
    public List<Record> getRecordMain(@SessionAttribute(value = LoginMember) Member loginMember) {
        return recordService.getRecords(loginMember.getId());
    }

    // todo sql문 작성해야함
    @GetMapping("/{category}")
    public List<Record> getCategoryMain(@SessionAttribute(value = LoginMember) Member loginMember, @PathVariable("category") String category) {
        return recordService.getRecordByCategory(loginMember.getId(), category);
    }

    @GetMapping("/latest")
    public List<Record> getLatestMain(@SessionAttribute(value = LoginMember) Member loginMember) {
        return recordService.getRecords(loginMember.getId());
    }

    @GetMapping("/designer")
    public List<Record> getDesignerMain(@SessionAttribute(value = LoginMember) Member loginMember, @RequestParam String DesignerName ) {
        return recordService.getRecordByDesigner(loginMember.getId(), DesignerName);
    }

}
