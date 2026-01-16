package uvt.tw.conferencemanagementsystem.api.dto.conference;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import uvt.tw.conferencemanagementsystem.api.dto.conference.enums.RegistrationStatus;

@Data
@Builder
public class RegistrationResponseDto {
  @NotNull Long conferenceId;

  @NotNull Long userId;

  RegistrationStatus status;

  LocalDate registrationDate;

  LocalDate confirmationDate;

  LocalDate cancellationDate;
}
