package uvt.tw.conferencemanagementsystem.app.conference.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uvt.tw.conferencemanagementsystem.api.dto.conference.enums.ConferenceStatus;
import uvt.tw.conferencemanagementsystem.app.session.model.SessionEntity;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;

@Entity
@Table(
    name = "conferences",
    indexes = {
      @Index(name = "idx_conferences_organizer_id", columnList = "organizer_id"),
      @Index(name = "idx_conferences_status", columnList = "status"),
      @Index(name = "idx_conferences_dates", columnList = "start_date, end_date")
    })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "organizer_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_conferences_organizer"))
  private UserEntity organizer;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "venue_name")
  private String venueName;

  @Column(name = "venue_address", columnDefinition = "TEXT")
  private String venueAddress;

  private String city;

  private String country;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @Column(name = "max_attendees")
  private Integer maxAttendees;

  @Column(name = "registration_deadline")
  private LocalDate registrationDeadline;

  @Column(length = 50)
  @Enumerated(EnumType.STRING)
  private ConferenceStatus status = ConferenceStatus.DRAFT;

  @Column(name = "website_url")
  private String websiteUrl;

  @Column(name = "cover_image_url")
  private String coverImageUrl;

  @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<SessionEntity> sessions = new HashSet<>();

  @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<RegistrationEntity> registrations = new HashSet<>();

  @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<ConferenceTagEntity> conferenceTags = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ConferenceEntity)) return false;
    ConferenceEntity that = (ConferenceEntity) o;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
