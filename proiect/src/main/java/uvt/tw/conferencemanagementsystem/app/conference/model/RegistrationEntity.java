package uvt.tw.conferencemanagementsystem.app.conference.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uvt.tw.conferencemanagementsystem.api.dto.conference.enums.RegistrationStatus;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;

@Entity
@Table(
    name = "registrations",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "uq_registrations_conference_user",
          columnNames = {"conference_id", "user_id"})
    },
    indexes = {
      @Index(name = "idx_registrations_conference_id", columnList = "conference_id"),
      @Index(name = "idx_registrations_user_id", columnList = "user_id"),
      @Index(name = "idx_registrations_status", columnList = "status")
    })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "conference_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_registrations_conference"))
  private ConferenceEntity conference;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "user_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_registrations_user"))
  private UserEntity user;

  @Column(length = 50)
  @Enumerated(EnumType.STRING)
  private RegistrationStatus status = RegistrationStatus.PENDING;

  @Column(name = "registration_date")
  private LocalDateTime registrationDate;

  @Column(name = "confirmation_date")
  private LocalDateTime confirmationDate;

  @Column(name = "cancellation_date")
  private LocalDateTime cancellationDate;

  @PrePersist
  protected void onCreate() {
    if (registrationDate == null) {
      registrationDate = LocalDateTime.now();
    }
  }
}
