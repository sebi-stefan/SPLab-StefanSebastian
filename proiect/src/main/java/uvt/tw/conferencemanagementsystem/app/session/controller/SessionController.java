package uvt.tw.conferencemanagementsystem.app.session.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uvt.tw.conferencemanagementsystem.api.SessionApi;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionResponseDto;
import uvt.tw.conferencemanagementsystem.app.session.service.SessionService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SessionController implements SessionApi {
  private final SessionService sessionService;

  @Override
  public ResponseEntity<SessionResponseDto> createSession(
      SessionRequestDto sessionRequestDto, Long conferenceId) {
    log.info(
        "Received request for createSession with {} and conference id: {}",
        sessionRequestDto,
        conferenceId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(sessionService.createSession(sessionRequestDto, conferenceId));
  }

  @Override
  public ResponseEntity<List<SessionResponseDto>> getAllSessions() {
    log.info("Received request for getAllSessions");
    return ResponseEntity.status(HttpStatus.OK).body(sessionService.getAllSessions());
  }

  @Override
  public ResponseEntity<SessionResponseDto> getSessionById(Long sessionId) {
    log.info("Received request for getSessionById with id: {}", sessionId);
    return ResponseEntity.status(HttpStatus.OK).body(sessionService.getSessionById(sessionId));
  }

  @Override
  public ResponseEntity<List<SessionResponseDto>> getSessionsByConferenceId(Long conferenceId) {
    log.info("Received request for getSessionsByConferenceId with id: {}", conferenceId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(sessionService.getSessionsByConferenceId(conferenceId));
  }

  @Override
  public ResponseEntity<SessionResponseDto> updateSession(
      SessionRequestDto sessionRequestDto, Long sessionId) {
    log.info("Received request for updateSession with id: {} and {}", sessionId, sessionRequestDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body(sessionService.updateSession(sessionRequestDto, sessionId));
  }

  @Override
  public ResponseEntity<Void> deleteSessionById(Long sessionId) {
    log.info("Received request for deleteSessionById with id: {}", sessionId);
    sessionService.deleteSessionById(sessionId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
