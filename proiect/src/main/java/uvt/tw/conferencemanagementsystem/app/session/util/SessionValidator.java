package uvt.tw.conferencemanagementsystem.app.session.util;

import java.time.DateTimeException;
import lombok.experimental.UtilityClass;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionRequestDto;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionEntity;

@UtilityClass
public class SessionValidator {

  public static void validateSessionData(SessionRequestDto sessionRequestDto) {
    if (sessionRequestDto.getEndTime().isBefore(sessionRequestDto.getStartTime())) {
      throw new DateTimeException("End time must be after start time");
    }
    if (sessionRequestDto.getMaxParticipants() < 0) {
      throw new IllegalArgumentException("Max participants must be greater than 0");
    }
  }

  public static void validateMaxParticipants(SessionEntity session) {
    int currentNoAttendees = session.getSessionAttendees().size();
    if (session.getMaxParticipants() != null
        && currentNoAttendees >= session.getMaxParticipants()) {
      throw new IllegalStateException(
          "Can't register more attendees. Max participants limit reached");
    }
  }
}
