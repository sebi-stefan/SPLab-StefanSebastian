package uvt.tw.conferencemanagementsystem.api.dto.conference;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uvt.tw.conferencemanagementsystem.api.dto.conference.enums.ConferenceStatus;
import uvt.tw.conferencemanagementsystem.api.dto.user.UserResponseDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceResponseDto {

  private Long id;

  private UserResponseDto organizer;

  private String title;

  private String description;

  private String venueName;

  private String venueAddress;

  private LocalDate startDate;

  private LocalDate endDate;

  private Integer maxAttendees;

  private LocalDate registrationDeadline;

  private ConferenceStatus status;

  private String websiteUrl;

  private String coverImageUrl;
}
