package jongjun.hairlog.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<ErrorResult> bindingExHandler(BindException exception) {
        ArrayList<ErrorResult> errorResults = new ArrayList<>();
        exception.getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            ErrorResult binding_exception = new ErrorResult("BindingException", field+" 필드 입력이 필요합니다. "+ defaultMessage);
            errorResults.add(binding_exception);
        });
        return errorResults;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalStateException.class})
    public ErrorResult illegalStateExHandler(IllegalStateException exception) {
        log.info("IllegalStateException = {}", exception.getClass());
        String defaultMessage = exception.getMessage();
        return new ErrorResult("IllegalStateException", defaultMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandler(Exception exception) {
        log.info("Exception = {}", exception.getClass());
        String defaultMessage = exception.getMessage();
        return new ErrorResult("Exception", defaultMessage);
    }

}
