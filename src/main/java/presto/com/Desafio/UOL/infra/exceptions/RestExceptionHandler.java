package presto.com.Desafio.UOL.infra.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import presto.com.Desafio.UOL.infra.exceptions.ApiErrorMessage;


import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(UnavailableCodenamesException.class)
    public ResponseEntity<ApiErrorMessage> handleUnavailableCodenamesException(UnavailableCodenamesException ex){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        String message = "Email já está em uso";

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(message);
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String exceptionMessage = ex.getMessage();
        String message;

        if (exceptionMessage.contains("Enum")){
            message = "Grupo inválido";
        }else if(exceptionMessage.contains("parse error")){
            message = "Json inválido.";
        }else{
            message = ex.getMessage();
        }

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(message);

        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = ex.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(errorMessages);
        return ResponseEntity.badRequest().body(apiErrorMessage);
    }
}
