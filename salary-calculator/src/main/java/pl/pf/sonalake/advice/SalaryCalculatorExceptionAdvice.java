package pl.pf.sonalake.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pl.pf.sonalake.api.model.response.ExceptionResponse;
import pl.pf.sonalake.exeption.NbpExeption;

/**
 * Odpowiedź dla sytuacji błędnych
 *
 * @author pfutro
  */
public class SalaryCalculatorExceptionAdvice {

    @RestControllerAdvice
    public class SalaryCalculatorExceptionHandlers {

        @ExceptionHandler(NbpExeption.class)
        public final ResponseEntity<Object> handleNbpExceptions(Exception ex, WebRequest request) {
            return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
        }


        @ExceptionHandler(Exception.class)
        public final ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
            return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
