package uvt.tw.conferencemanagementsystem.api.exception;

public class SessionNotFoundException extends RuntimeException {
  public SessionNotFoundException(String message) {
    super(message);
  }
}
