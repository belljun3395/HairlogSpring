package jongjun.hairlog.web.controller.record;

import jongjun.hairlog.domain.member.Member;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.service.serviceInterface.RecordService;
import jongjun.hairlog.web.dto.get.GetDesignerDTO;
import jongjun.hairlog.web.dto.get.GetRecordDTO;
import jongjun.hairlog.web.dto.method.Transfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static jongjun.hairlog.web.SessionConst.LoginMember;
import static jongjun.hairlog.web.dto.method.Transfer.recordsToListDTO;

@Slf4j
@RestController
@RequestMapping("/api/record/sort")
@RequiredArgsConstructor
public class RecordSortController {

    private final RecordService recordService;

    @GetMapping("/latest")
    public List<GetRecordDTO> getLatestMain(@SessionAttribute(value = LoginMember) Member loginMember) {
        List<Record> records = recordService.getRecords(loginMember.getId());
        ArrayList<GetRecordDTO> getRecordDTOS = recordsToListDTO(records);
        return getRecordDTOS;
    }


    // todo sql문 작성해야함
    @GetMapping("/{category}")
    public List<GetRecordDTO> getCategoryMain(@SessionAttribute(value = LoginMember) Member loginMember, @PathVariable("category") String category) {
        List<Record> records = recordService.getRecordByCategory(loginMember.getId(), category);
        ArrayList<GetRecordDTO> getRecordDTOS = recordsToListDTO(records);
        return getRecordDTOS;
    }

    // todo sql문 작성해야함
    @GetMapping("/designer")
    public List<GetRecordDTO> getDesignerMain(@SessionAttribute(value = LoginMember) Member loginMember, @RequestParam String DesignerName) {
        List<Record> recordByDesigner = recordService.getRecordByDesigner(loginMember.getId(), DesignerName);
        ArrayList<GetRecordDTO> getRecordDTOS = recordsToListDTO(recordByDesigner);
        return getRecordDTOS;
    }

}
