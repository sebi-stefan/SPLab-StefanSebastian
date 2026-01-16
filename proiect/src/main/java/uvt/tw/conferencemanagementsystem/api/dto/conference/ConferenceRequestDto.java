package uvt.tw.conferencemanagementsystem.api.dto.conference;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConferenceRequestDto {

  @Size(max = 255)
  @NotNull
  private String title;

  @Size(max = 255)
  private String description;

  @Size(max = 255)
  private String venueName;

  @Size(max = 255)
  private String venueAddress;

  @Size(max = 20)
  private String city;

  @Size(max = 20)
  private String country;

  @NotNull private LocalDate startDate;

  @NotNull private LocalDate endDate;

  private Integer maxAttendees;

  @NotNull private LocalDate registrationDeadline;

  @Size(max = 50)
  private String websiteUrl;
}
