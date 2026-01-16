package uvt.tw.conferencemanagementsystem.app.conference.util;

import java.time.DateTimeException;
import lombok.experimental.UtilityClass;
import uvt.tw.conferencemanagementsystem.api.dto.conference.ConferenceRequestDto;

@UtilityClass
public class ConferenceValidator {

  public static void validateConferenceData(ConferenceRequestDto requestDto) {

    if (requestDto.getEndDate().isBefore(requestDto.getStartDate())) {
      throw new DateTimeException("End Date must be after Start Date!");
    }
    if (requestDto.getRegistrationDeadline().isAfter(requestDto.getEndDate())) {
      throw new DateTimeException("Registration Deadline must be before End Date!");
    }
  }
}
