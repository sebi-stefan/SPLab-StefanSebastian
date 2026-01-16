package uvt.tw.conferencemanagementsystem.app.session.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;

@Entity
@Table(
    name = "session_attendees",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "uq_session_attendees_session_user",
          columnNames = {"session_id", "user_id"})
    },
    indexes = {
      @Index(name = "idx_session_attendees_session_id", columnList = "session_id"),
      @Index(name = "idx_session_attendees_user_id", columnList = "user_id")
    })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionAttendeeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "session_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_session_attendees_session"))
  private SessionEntity session;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
      name = "user_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_session_attendees_user"))
  private UserEntity user;

  @Column(name = "registered_at")
  private LocalDateTime registeredAt;

  private Boolean attended = false;

  @PrePersist
  protected void onCreate() {
    if (registeredAt == null) {
      registeredAt = LocalDateTime.now();
    }
  }
}
