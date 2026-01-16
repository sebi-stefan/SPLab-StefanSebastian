package uvt.tw.conferencemanagementsystem.app.conference.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uvt.tw.conferencemanagementsystem.api.ConferenceApi;
import uvt.tw.conferencemanagementsystem.api.dto.conference.ConferenceRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.conference.ConferenceResponseDto;
import uvt.tw.conferencemanagementsystem.api.dto.conference.ConferenceStatusUpdateDto;
import uvt.tw.conferencemanagementsystem.app.conference.service.ConferenceService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ConferenceController implements ConferenceApi {

  private final ConferenceService conferenceService;

  @Override
  public ResponseEntity<ConferenceResponseDto> createConference(
      ConferenceRequestDto conferenceRequestDto) {
    log.info("Received request for createConference with {}", conferenceRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(conferenceService.createConference(conferenceRequestDto));
  }

  @Override
  public ResponseEntity<ConferenceResponseDto> updateConference(
      ConferenceRequestDto conferenceRequestDto, Long conferenceId) {
    log.info(
        "Received request for updateConference with id: {} and {}",
        conferenceId,
        conferenceRequestDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body(conferenceService.updateConference(conferenceRequestDto, conferenceId));
  }

  @Override
  public ResponseEntity<List<ConferenceResponseDto>> getAllConferences() {
    log.info("Received request for getAllConferences");
    return ResponseEntity.status(HttpStatus.OK).body(conferenceService.getAllConferences());
  }

  @Override
  public ResponseEntity<ConferenceResponseDto> getConferenceById(Long conferenceId) {
    log.info("Received request for getConferenceById with id: {}", conferenceId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(conferenceService.getConferenceById(conferenceId));
  }

  @Override
  public ResponseEntity<List<ConferenceResponseDto>> getConferencesByUserId(Long userId) {
    log.info("Received request for getConferencesByUserId with id: {}", userId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(conferenceService.getConferencesByUserId(userId));
  }

  @Override
  public ResponseEntity<List<ConferenceResponseDto>> getCurrentUserConferences() {
    log.info("Received request for getCurrentUserConfereces");
    return ResponseEntity.status(HttpStatus.OK).body(conferenceService.getCurrentUserConferences());
  }

  @Override
  public ResponseEntity<Void> deleteConferenceById(Long conferenceId) {
    log.info("Received request for deleteConfereceById with id: {}", conferenceId);
    conferenceService.deleteConferenceById(conferenceId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @Override
  public ResponseEntity<ConferenceResponseDto> updateConferenceStatus(
      ConferenceStatusUpdateDto conferenceStatusUpdateDto, Long conferenceId) {
    log.info(
        "Received request for updateConferenceStatus with {} for conference with id: {}",
        conferenceStatusUpdateDto,
        conferenceId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(conferenceService.updateConferenceStatus(conferenceStatusUpdateDto, conferenceId));
  }
}
