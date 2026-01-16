package uvt.tw.conferencemanagementsystem.app.session.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionResponseDto;
import uvt.tw.conferencemanagementsystem.api.exception.ConferenceNotFoundException;
import uvt.tw.conferencemanagementsystem.api.exception.SessionNotFoundException;
import uvt.tw.conferencemanagementsystem.app.conference.model.ConferenceEntity;
import uvt.tw.conferencemanagementsystem.app.conference.repository.ConferenceRepository;
import uvt.tw.conferencemanagementsystem.app.conference.service.ConferenceService;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionEntity;
import uvt.tw.conferencemanagementsystem.app.session.repository.SessionRepository;
import uvt.tw.conferencemanagementsystem.app.session.util.SessionConverter;
import uvt.tw.conferencemanagementsystem.app.session.util.SessionValidator;

@Service
@RequiredArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;
  private final ConferenceRepository conferenceRepository;
  private final ConferenceService conferenceService;

  @Transactional
  public SessionResponseDto createSession(SessionRequestDto sessionRequestDto, Long conferenceId) {
    SessionValidator.validateSessionData(sessionRequestDto);
    ConferenceEntity conference =
        conferenceRepository
            .findById(conferenceId)
            .orElseThrow(
                () ->
                    new ConferenceNotFoundException(
                        String.format("Conference with id: %d not found", conferenceId)));
    conferenceService.validateConferenceOwnership(conference);

    SessionEntity session = new SessionEntity();
    updateSessionData(sessionRequestDto, session);

    session.setConference(conference);

    SessionEntity createdSession = sessionRepository.saveAndFlush(session);
    return SessionConverter.convertToResponseDto(createdSession);
  }

  public List<SessionResponseDto> getAllSessions() {
    return sessionRepository.findAll().stream()
        .map(SessionConverter::convertToResponseDto)
        .toList();
  }

  public SessionResponseDto getSessionById(Long sessionId) {
    SessionEntity session =
        sessionRepository
            .findById(sessionId)
            .orElseThrow(
                () ->
                    new SessionNotFoundException(
                        String.format("Session with id: %d not found", sessionId)));
    return SessionConverter.convertToResponseDto(session);
  }

  public List<SessionResponseDto> getSessionsByConferenceId(Long conferenceId) {
    if (!conferenceRepository.existsById(conferenceId))
      throw new ConferenceNotFoundException(
          String.format("Conference with id: %d does not exist!", conferenceId));
    List<SessionEntity> sessions = sessionRepository.getSessionEntitiesByConferenceId(conferenceId);
    return sessions.stream().map(SessionConverter::convertToResponseDto).toList();
  }

  public SessionResponseDto updateSession(SessionRequestDto sessionRequestDto, Long sessionId) {
    SessionValidator.validateSessionData(sessionRequestDto);
    SessionEntity session =
        sessionRepository
            .findById(sessionId)
            .orElseThrow(
                () ->
                    new SessionNotFoundException(
                        String.format("Session with id: %d not found", sessionId)));
    conferenceService.validateConferenceOwnership(session.getConference());

    updateSessionData(sessionRequestDto, session);

    SessionEntity updatedSession = sessionRepository.saveAndFlush(session);
    return SessionConverter.convertToResponseDto(updatedSession);
  }

  public void deleteSessionById(Long sessionId) {
    SessionEntity session =
        sessionRepository
            .findById(sessionId)
            .orElseThrow(
                () ->
                    new SessionNotFoundException(
                        String.format("Session with id: %d not found", sessionId)));
    sessionRepository.deleteById(session.getId());
  }

  private void updateSessionData(SessionRequestDto sessionRequestDto, SessionEntity session) {
    session.setTitle(sessionRequestDto.getTitle());
    session.setDescription(sessionRequestDto.getDescription());
    session.setRoom(sessionRequestDto.getRoom());
    session.setStartTime(sessionRequestDto.getStartTime());
    session.setEndTime(sessionRequestDto.getEndTime());
    session.setMaxParticipants(sessionRequestDto.getMaxParticipants());
    session.setSessionType(sessionRequestDto.getSessionType());
  }
}
