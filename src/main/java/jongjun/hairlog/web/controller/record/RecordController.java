package jongjun.hairlog.web.controller.record;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.repository.repositoryInterface.RecordRepository;
import jongjun.hairlog.service.serviceInterface.RecordService;
import jongjun.hairlog.web.dto.CutDTO;
import jongjun.hairlog.web.dto.RecordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jongjun.hairlog.web.SessionConst.LoginMember;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;


    @GetMapping("/record/{id}")
    public Record getRecord(@SessionAttribute(value = LoginMember) Member loginMember, @PathVariable("id") Long id) {
        return recordService.getRecordById(loginMember.getId(), id);
    }

    // todo createdAt 기준으로 하나만 불러오기로 수정
    // note 현재 : 전체 리스트를 불러오고 가장 마지막 기록 가지고 옴
    @GetMapping("/result")
    public Record getResult(@SessionAttribute(value = LoginMember) Member loginMember) {
        List<Record> records = recordService.getRecords(loginMember.getId());
        return records.get(records.size() - 1);
    }

    @PostMapping("recordDelete")
    public boolean deleteRecord(@SessionAttribute(value = LoginMember) Member loginMember, @RequestParam("RecordId") Long recordId) {
        return recordService.deleteRecord(loginMember, recordId);
    }
}
