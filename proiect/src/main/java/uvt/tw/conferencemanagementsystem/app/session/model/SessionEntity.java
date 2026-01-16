package uvt.tw.conferencemanagementsystem.app.session.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uvt.tw.conferencemanagementsystem.api.dto.session.enums.SessionType;
import uvt.tw.conferencemanagementsystem.app.conference.model.ConferenceEntity;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;

@Entity
@Table(
    name = "sessions",
    indexes = {
      @Index(name = "idx_sessions_conference_id", columnList = "conference_id"),
      @Index(name = "idx_sessions_time", columnList = "start_time, end_time")
    })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "conference_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_sessions_conference"))
  private ConferenceEntity conference;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "session_type", length = 50)
  private SessionType sessionType;

  private String room;

  @Column(name = "start_time", nullable = false)
  private LocalDateTime startTime;

  @Column(name = "end_time", nullable = false)
  private LocalDateTime endTime;

  @Column(name = "max_participants")
  private Integer maxParticipants;

  @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<SessionAttendeeEntity> sessionAttendees = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "speaker",
      joinColumns =
          @JoinColumn(
              name = "session_id",
              foreignKey = @ForeignKey(name = "speaker_session_id_fkey")),
      inverseJoinColumns =
          @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "speaker_user_id_fkey")))
  private Set<UserEntity> speakers = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SessionEntity)) return false;
    SessionEntity that = (SessionEntity) o;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
