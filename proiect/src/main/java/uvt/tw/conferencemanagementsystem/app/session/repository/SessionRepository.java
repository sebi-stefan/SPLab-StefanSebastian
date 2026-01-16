package uvt.tw.conferencemanagementsystem.app.session.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionEntity;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

  List<SessionEntity> getSessionEntitiesByConferenceId(Long id);
}
