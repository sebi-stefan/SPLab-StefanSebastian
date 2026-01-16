package uvt.tw.conferencemanagementsystem.api.exception;

public class StatusNotValidException extends RuntimeException {
  public StatusNotValidException(String message) {
    super(message);
  }
}
