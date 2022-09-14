package jongjun.hairlog.web.controller.record;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.service.serviceInterface.RecordService;
import jongjun.hairlog.web.dto.get.GetRecordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static jongjun.hairlog.web.SessionConst.LoginMember;

@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/{id}")
    public GetRecordDTO getRecord(@SessionAttribute(value = LoginMember) Member loginMember, @PathVariable("id") Long id) {
        Record recordById = recordService.getRecordById(loginMember.getId(), id);
        return recordById.toDTO();
    }

    // todo createdAt 기준으로 하나만 불러오기로 수정
    // note 현재 : 전체 리스트를 불러오고 가장 마지막 기록 가지고 옴
    @GetMapping("/result")
    public GetRecordDTO getResult(@SessionAttribute(value = LoginMember) Member loginMember) {
        List<Record> records = recordService.getRecords(loginMember.getId());
        Record record = records.get(records.size() - 1);
        return record.toDTO();
    }

    @PostMapping("/delete")
    public boolean deleteRecord(@SessionAttribute(value = LoginMember) Member loginMember, @RequestParam("RecordId") Long recordId) {
        return recordService.deleteRecord(loginMember, recordId);
    }
}
