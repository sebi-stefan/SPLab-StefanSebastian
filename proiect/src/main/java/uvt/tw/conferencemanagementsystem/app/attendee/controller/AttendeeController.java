package uvt.tw.conferencemanagementsystem.app.attendee.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uvt.tw.conferencemanagementsystem.api.AttendeeApi;
import uvt.tw.conferencemanagementsystem.api.dto.attendee.SessionAttendeesDto;
import uvt.tw.conferencemanagementsystem.app.attendee.service.AttendeeService;
import uvt.tw.conferencemanagementsystem.app.user.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AttendeeController implements AttendeeApi {

  private final AttendeeService attendeeService;
  private final UserService userService;

  @Override
  public ResponseEntity<List<SessionAttendeesDto>> getConferenceAttendees(Long conferenceId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(attendeeService.getConferenceAttendees(conferenceId));
  }

  @Override
  public ResponseEntity<SessionAttendeesDto> getSessionAttendees(Long sessionId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(attendeeService.getSessionAttendees(sessionId));
  }

  @Override
  public ResponseEntity<SessionAttendeesDto> attendSession(Long sessionId) {
    log.info(
        "Received request for attendSession with id: {} from user {}",
        sessionId,
        userService.getCurrentUser().getEmail());
    return ResponseEntity.status(HttpStatus.OK)
        .body(attendeeService.attendSession(sessionId, userService.getCurrentUser().getId()));
  }

  @Override
  public ResponseEntity<Void> cancelAttendSession(Long sessionId) {
    log.info(
        "Received request for cancelAttendSession with id: {} from user {}",
        sessionId,
        userService.getCurrentUser().getId());
    attendeeService.cancelAttendSession(sessionId, userService.getCurrentUser().getId());
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
