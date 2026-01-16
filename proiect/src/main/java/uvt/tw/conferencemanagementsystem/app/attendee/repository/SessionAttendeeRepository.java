package uvt.tw.conferencemanagementsystem.app.attendee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionAttendeeEntity;

@Repository
public interface SessionAttendeeRepository extends JpaRepository<SessionAttendeeEntity, Long> {

  @Modifying
  @Query(
      "DELETE FROM SessionAttendeeEntity sa WHERE sa.session.id = :sessionId AND sa.user.id = :userId")
  int deleteBySessionIdAndUserId(@Param("sessionId") Long sessionId, @Param("userId") Long userId);
}
