package uvt.tw.conferencemanagementsystem.api.dto.session;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import uvt.tw.conferencemanagementsystem.api.dto.session.enums.SessionType;
import uvt.tw.conferencemanagementsystem.api.dto.user.UserResponseDto;

@Data
@Builder
public class SessionResponseDto {
  private Long id;
  private String title;
  private String description;
  private SessionType sessionType;
  private String room;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Integer maxParticipants;
  private Set<UserResponseDto> speakers;
}
