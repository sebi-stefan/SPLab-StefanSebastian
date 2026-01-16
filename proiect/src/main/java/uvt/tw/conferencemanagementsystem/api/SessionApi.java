package uvt.tw.conferencemanagementsystem.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionResponseDto;

@RestController
@RequestMapping("/session")
@Tag(name = "Session")
public interface SessionApi {

  @PostMapping("/create/{conferenceId}")
  ResponseEntity<SessionResponseDto> createSession(
      @RequestBody @Valid SessionRequestDto sessionRequestDto, @PathVariable Long conferenceId);

  @GetMapping
  ResponseEntity<List<SessionResponseDto>> getAllSessions();

  @GetMapping("/{sessionId}")
  ResponseEntity<SessionResponseDto> getSessionById(@PathVariable Long sessionId);

  @GetMapping("conference/{conferenceId}")
  ResponseEntity<List<SessionResponseDto>> getSessionsByConferenceId(
      @PathVariable Long conferenceId);

  @PostMapping("/{sessionId}")
  ResponseEntity<SessionResponseDto> updateSession(
      @RequestBody @Valid SessionRequestDto sessionRequestDto, @PathVariable Long sessionId);

  @DeleteMapping("/{sessionId}")
  ResponseEntity<Void> deleteSessionById(@PathVariable Long sessionId);
}
