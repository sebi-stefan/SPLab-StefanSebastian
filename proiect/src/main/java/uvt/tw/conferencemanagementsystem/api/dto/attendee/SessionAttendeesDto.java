package uvt.tw.conferencemanagementsystem.api.dto.attendee;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionResponseDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.UserResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionAttendeesDto {

  private SessionResponseDto session;
  private List<UserResponseDto> attendees;
}
