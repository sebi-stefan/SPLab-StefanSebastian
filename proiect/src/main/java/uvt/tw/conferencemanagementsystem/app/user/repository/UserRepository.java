package uvt.tw.conferencemanagementsystem.app.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  boolean existsByEmail(String email);
}
