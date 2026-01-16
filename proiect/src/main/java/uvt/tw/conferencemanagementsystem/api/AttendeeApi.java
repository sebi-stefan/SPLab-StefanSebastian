package uvt.tw.conferencemanagementsystem.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uvt.tw.conferencemanagementsystem.api.dto.attendee.SessionAttendeesDto;

@RestController
@RequestMapping("/attendee")
@Tag(name = "Attendee")
public interface AttendeeApi {

  @GetMapping("/conference/{conferenceId}")
  ResponseEntity<List<SessionAttendeesDto>> getConferenceAttendees(@PathVariable Long conferenceId);

  @GetMapping("/session/{sessionId}")
  ResponseEntity<SessionAttendeesDto> getSessionAttendees(@PathVariable Long sessionId);

  @PostMapping("/session/{sessionId}")
  ResponseEntity<SessionAttendeesDto> attendSession(@PathVariable Long sessionId);

  @PostMapping("/session/cancel/{sessionId}")
  ResponseEntity<Void> cancelAttendSession(@PathVariable Long sessionId);
}
