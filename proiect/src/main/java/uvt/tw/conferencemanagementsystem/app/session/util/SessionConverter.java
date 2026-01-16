package uvt.tw.conferencemanagementsystem.app.session.util;

import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import uvt.tw.conferencemanagementsystem.api.dto.session.SessionResponseDto;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionEntity;
import uvt.tw.conferencemanagementsystem.app.user.util.UserConverter;

@UtilityClass
public class SessionConverter {

  public static SessionResponseDto convertToResponseDto(SessionEntity entity) {
    return SessionResponseDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .sessionType(entity.getSessionType())
        .room(entity.getRoom())
        .startTime(entity.getStartTime())
        .endTime(entity.getEndTime())
        .maxParticipants(entity.getMaxParticipants())
        .speakers(
            entity.getSpeakers().stream()
                .map(UserConverter::convertToReponseDto)
                .collect(Collectors.toSet())) // UserConverter accepta doar Entity, nu set
        .build();
  }
}
