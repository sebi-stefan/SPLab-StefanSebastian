package uvt.tw.conferencemanagementsystem.app.conference.util;

import lombok.experimental.UtilityClass;
import uvt.tw.conferencemanagementsystem.api.dto.conference.ConferenceResponseDto;
import uvt.tw.conferencemanagementsystem.app.conference.model.ConferenceEntity;
import uvt.tw.conferencemanagementsystem.app.user.util.UserConverter;

@UtilityClass
public class ConferenceConverter {

  public static ConferenceResponseDto convertToResponseDto(ConferenceEntity entity) {
    return ConferenceResponseDto.builder()
        .id(entity.getId())
        .organizer(UserConverter.convertToReponseDto(entity.getOrganizer()))
        .title(entity.getTitle())
        .description(entity.getDescription())
        .venueName(entity.getVenueName())
        .venueAddress(entity.getVenueAddress())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .maxAttendees(entity.getMaxAttendees())
        .registrationDeadline(entity.getRegistrationDeadline())
        .status(entity.getStatus())
        .websiteUrl(entity.getWebsiteUrl())
        .coverImageUrl(entity.getCoverImageUrl())
        .build();
  }
}
