package jongjun.hairlog.web.dto.method;

import java.text.ParseException;
import java.time.LocalDate;

public class Transfer {
    public static LocalDate toLocalDateTime(String stringDate) throws ParseException {
        return LocalDate.parse(stringDate);
    }
}
