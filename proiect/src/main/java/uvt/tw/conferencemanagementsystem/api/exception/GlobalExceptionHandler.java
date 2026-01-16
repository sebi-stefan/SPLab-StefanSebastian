package uvt.tw.conferencemanagementsystem.api.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @Getter
  public static class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, List<String> errors) {
      this.status = status;
      this.message = message;
      this.errors = errors;
      this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message, String error) {
      this.status = status;
      this.message = message;
      this.errors = new ArrayList<>(List.of(error));
      this.timestamp = LocalDateTime.now();
    }
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex) {

    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList();

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", errors);

    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex) {

    List<String> errors = new ArrayList<>();

    if (ex.getCause() instanceof InvalidFormatException) {
      InvalidFormatException ife = (InvalidFormatException) ex.getCause();

      if (ife.getTargetType() != null && ife.getTargetType().isEnum()) {
        String fieldName = ife.getPath().get(0).getFieldName();
        Object invalidValue = ife.getValue();
        Class<?> enumClass = ife.getTargetType();

        String allowedValues =
            Arrays.stream(enumClass.getEnumConstants())
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        errors.add(
            fieldName
                + ": Invalid value '"
                + invalidValue
                + "'. Allowed values are: ["
                + allowedValues
                + "]");
      } else {
        errors.add("Invalid format for field: " + ife.getPath().get(0).getFieldName());
      }
    } else {
      errors.add("Malformed JSON request");
    }

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", errors);

    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {

    ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), List.of());

    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(UserNotFoundException.class)
  protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {

    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());

    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(DateTimeException.class)
  protected ResponseEntity<Object> handleDateTimeException(DateTimeException ex) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(ConferenceNotFoundException.class)
  protected ResponseEntity<Object> handleConferenceNotFoundException(
      ConferenceNotFoundException ex) {

    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());

    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(OwnershipException.class)
  protected ResponseEntity<Object> handleOwnershipException(OwnershipException ex) {
    ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), List.of());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(SessionNotFoundException.class)
  protected ResponseEntity<Object> handleSessionNotFoundException(SessionNotFoundException ex) {

    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());

    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(StatusNotValidException.class)
  protected ResponseEntity<Object> handleStatusNotValidException(StatusNotValidException ex) {

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of());

    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}
