package uvt.tw.conferencemanagementsystem.app.conference.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uvt.tw.conferencemanagementsystem.api.dto.conference.enums.ConferenceStatus;
import uvt.tw.conferencemanagementsystem.app.conference.model.ConferenceEntity;

public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {

  List<ConferenceEntity> getConferenceEntitiesByOrganizerId(Long id);

  List<ConferenceEntity> getConferenceEntitiesByStatusInAndStartDate(
      List<ConferenceStatus> statuses, LocalDate startDate);

  List<ConferenceEntity> getConferenceEntitiesByStatusInAndEndDate(
      List<ConferenceStatus> statuses, LocalDate endDate);

  @Query(
      "SELECT DISTINCT c FROM ConferenceEntity c "
          + "LEFT JOIN FETCH c.sessions s "
          + "LEFT JOIN FETCH s.sessionAttendees sa "
          + "LEFT JOIN FETCH sa.user "
          + "WHERE c.id = :conferenceId")
  Optional<ConferenceEntity> findByIdWithSessionsAndAttendees(
      @Param("conferenceId") Long conferenceId);
}
