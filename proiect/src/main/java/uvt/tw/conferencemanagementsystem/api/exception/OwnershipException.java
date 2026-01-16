package uvt.tw.conferencemanagementsystem.api.exception;

public class OwnershipException extends RuntimeException {
  public OwnershipException() {
    super("Access forbidden!");
  }
}
