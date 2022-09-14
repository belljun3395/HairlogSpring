package jongjun.hairlog.web.dto.method;

import jongjun.hairlog.domain.designer.Designer;
import jongjun.hairlog.domain.record.Record;
import jongjun.hairlog.web.dto.get.GetDesignerDTO;
import jongjun.hairlog.web.dto.get.GetRecordDTO;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transfer {
    public static LocalDate toLocalDateTime(String stringDate) throws ParseException {
        return LocalDate.parse(stringDate);
    }
    public static ArrayList<GetRecordDTO> recordsToListDTO(List<Record> records) {
        ArrayList<GetRecordDTO> recordDTOS = new ArrayList<>();
        for (Record record : records) {
            GetRecordDTO getRecordDTO = record.toDTO();
            recordDTOS.add(getRecordDTO);
        }
        return recordDTOS;
    }

    public static ArrayList<GetDesignerDTO> designersToListDTO(List<Designer> designers) {
        ArrayList<GetDesignerDTO> designerDTOS = new ArrayList<>();
        for (
                Designer designer : designers) {
            GetDesignerDTO getDesignerDTO = designer.toDTO();
            designerDTOS.add(getDesignerDTO);
        }
        return designerDTOS;
    }

}
