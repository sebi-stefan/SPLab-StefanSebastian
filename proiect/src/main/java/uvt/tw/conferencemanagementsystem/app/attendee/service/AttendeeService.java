package uvt.tw.conferencemanagementsystem.app.attendee.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uvt.tw.conferencemanagementsystem.api.dto.attendee.SessionAttendeesDto;
import uvt.tw.conferencemanagementsystem.api.exception.ConferenceNotFoundException;
import uvt.tw.conferencemanagementsystem.api.exception.SessionNotFoundException;
import uvt.tw.conferencemanagementsystem.api.exception.UserAlreadyExistsException;
import uvt.tw.conferencemanagementsystem.api.exception.UserNotFoundException;
import uvt.tw.conferencemanagementsystem.app.attendee.repository.SessionAttendeeRepository;
import uvt.tw.conferencemanagementsystem.app.conference.model.ConferenceEntity;
import uvt.tw.conferencemanagementsystem.app.conference.repository.ConferenceRepository;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionAttendeeEntity;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionEntity;
import uvt.tw.conferencemanagementsystem.app.session.repository.SessionRepository;
import uvt.tw.conferencemanagementsystem.app.session.util.SessionConverter;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;
import uvt.tw.conferencemanagementsystem.app.user.repository.UserRepository;
import uvt.tw.conferencemanagementsystem.app.user.util.UserConverter;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendeeService {

  private final SessionRepository sessionRepository;
  private final ConferenceRepository conferenceRepository;
  private final UserRepository userRepository;
  private final SessionAttendeeRepository sessionAttendeeRepository;

  @Transactional(readOnly = true)
  public List<SessionAttendeesDto> getConferenceAttendees(Long conferenceId) {
    ConferenceEntity conference =
        conferenceRepository
            .findById(conferenceId)
            .orElseThrow(
                () ->
                    new ConferenceNotFoundException(
                        String.format("Conference with id: %d not found", conferenceId)));
    List<SessionAttendeesDto> conferenceAttendees = new ArrayList<>();
    for (SessionEntity s : conference.getSessions()) {
      SessionAttendeesDto sessionAttendee = new SessionAttendeesDto();
      sessionAttendee.setSession(SessionConverter.convertToResponseDto(s));
      sessionAttendee.setAttendees(
          s.getSessionAttendees().stream()
              .map(attendee -> UserConverter.convertToReponseDto(attendee.getUser()))
              .toList());
      conferenceAttendees.add(sessionAttendee);
    }
    return conferenceAttendees;
  }

  public SessionAttendeesDto getSessionAttendees(Long sessionId) {
    SessionEntity session =
        sessionRepository
            .findById(sessionId)
            .orElseThrow(
                () ->
                    new SessionNotFoundException(
                        String.format("Session with id: %d not found", sessionId)));
    return SessionAttendeesDto.builder()
        .session(SessionConverter.convertToResponseDto(session))
        .attendees(
            session.getSessionAttendees().stream()
                .map(attendee -> UserConverter.convertToReponseDto(attendee.getUser()))
                .toList())
        .build();
  }

  @Transactional
  public SessionAttendeesDto attendSession(Long sessionId, Long userId) {
    SessionEntity session =
        sessionRepository
            .findById(sessionId)
            .orElseThrow(
                () ->
                    new SessionNotFoundException(
                        String.format("Session with id: %d not found", sessionId)));
    UserEntity user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () ->
                    new UserNotFoundException(String.format("User with id: %d not found", userId)));
    session.getSessionAttendees().stream()
        .filter(x -> x.getUser().equals(user))
        .findAny()
        .ifPresent(
            x -> {
              throw new UserAlreadyExistsException("User is already registered to this session");
            });

    SessionAttendeeEntity sessionAttendee = new SessionAttendeeEntity();
    sessionAttendee.setSession(session);
    sessionAttendee.setUser(user);
    sessionAttendee.setRegisteredAt(LocalDateTime.now());

    session.getSessionAttendees().add(sessionAttendee);
    user.getSessionAttendees().add(sessionAttendee);

    return SessionAttendeesDto.builder()
        .session(SessionConverter.convertToResponseDto(session))
        .attendees(List.of(UserConverter.convertToReponseDto(user)))
        .build();
  }

  @Transactional
  public void cancelAttendSession(Long sessionId, Long userId) {
    if (!sessionRepository.existsById(sessionId)) {
      throw new SessionNotFoundException(String.format("Session with id: %d not found", sessionId));
    }

    if (!userRepository.existsById(userId)) {
      throw new UserNotFoundException(String.format("User with id: %d not found", userId));
    }

    int deleted = sessionAttendeeRepository.deleteBySessionIdAndUserId(sessionId, userId);

    if (deleted == 0) {
      throw new UserNotFoundException("User does not attend this session!");
    }
  }
}
