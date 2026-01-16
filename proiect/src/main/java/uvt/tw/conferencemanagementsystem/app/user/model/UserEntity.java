package uvt.tw.conferencemanagementsystem.app.user.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uvt.tw.conferencemanagementsystem.api.dto.user.enums.UserRole;
import uvt.tw.conferencemanagementsystem.app.conference.model.ConferenceEntity;
import uvt.tw.conferencemanagementsystem.app.conference.model.RegistrationEntity;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionAttendeeEntity;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionEntity;

@Entity
@Table(
    name = "users",
    indexes = {@Index(name = "idx_users_email", columnList = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @Column(nullable = false, length = 50)
  @Enumerated(EnumType.STRING)
  private UserRole role = UserRole.ATTENDEE;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(columnDefinition = "TEXT")
  private String bio;

  private String organization;

  @Column(name = "profile_picture_url")
  private String profilePictureUrl;

  @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<ConferenceEntity> organizedConferences = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<RegistrationEntity> registrations = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<SessionAttendeeEntity> sessionAttendees = new ArrayList<>();

  @ManyToMany(mappedBy = "speakers", fetch = FetchType.LAZY)
  private Set<SessionEntity> speakingSessions = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserEntity)) return false;
    UserEntity that = (UserEntity) o;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
