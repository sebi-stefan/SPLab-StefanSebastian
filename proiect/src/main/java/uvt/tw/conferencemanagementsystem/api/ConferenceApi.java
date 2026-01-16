package uvt.tw.conferencemanagementsystem.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uvt.tw.conferencemanagementsystem.api.dto.conference.ConferenceRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.conference.ConferenceResponseDto;
import uvt.tw.conferencemanagementsystem.api.dto.conference.ConferenceStatusUpdateDto;

@RestController
@RequestMapping("/conference")
@Tag(name = "Conference")
public interface ConferenceApi {

  @PostMapping
  ResponseEntity<ConferenceResponseDto> createConference(
      @RequestBody @Valid ConferenceRequestDto conferenceRequestDto);

  @PostMapping("/{conferenceId}")
  ResponseEntity<ConferenceResponseDto> updateConference(
      @RequestBody @Valid ConferenceRequestDto conferenceRequestDto,
      @PathVariable Long conferenceId);

  @GetMapping
  ResponseEntity<List<ConferenceResponseDto>> getAllConferences();

  @GetMapping("/{conferenceId}")
  ResponseEntity<ConferenceResponseDto> getConferenceById(@PathVariable Long conferenceId);

  @GetMapping("/user/{userId}")
  ResponseEntity<List<ConferenceResponseDto>> getConferencesByUserId(@PathVariable Long userId);

  @GetMapping("/currentUser")
  ResponseEntity<List<ConferenceResponseDto>> getCurrentUserConferences();

  @DeleteMapping("/{conferenceId}")
  ResponseEntity<Void> deleteConferenceById(@PathVariable Long conferenceId);

  @PostMapping("/update/{conferenceId}")
  ResponseEntity<ConferenceResponseDto> updateConferenceStatus(
      @RequestBody @Valid ConferenceStatusUpdateDto conferenceStatusUpdateDto,
      @PathVariable Long conferenceId);
}
