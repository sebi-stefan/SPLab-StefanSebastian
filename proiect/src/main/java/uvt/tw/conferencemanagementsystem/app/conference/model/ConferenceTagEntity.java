package uvt.tw.conferencemanagementsystem.app.conference.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "conference_tags",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "uq_conference_tags_conference_tag",
          columnNames = {"conference_id", "tag_id"})
    },
    indexes = {
      @Index(name = "idx_conference_tags_conference_id", columnList = "conference_id"),
      @Index(name = "idx_conference_tags_tag_id", columnList = "tag_id")
    })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceTagEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "conference_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_conference_tags_conference"))
  private ConferenceEntity conference;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "tag_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_conference_tags_tag"))
  private TagEntity tag;
}
