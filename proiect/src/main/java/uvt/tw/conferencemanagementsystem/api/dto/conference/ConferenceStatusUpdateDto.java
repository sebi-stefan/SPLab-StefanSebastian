package uvt.tw.conferencemanagementsystem.api.dto.conference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import uvt.tw.conferencemanagementsystem.api.dto.conference.enums.ConferenceStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceStatusUpdateDto {

  @NotNull private ConferenceStatus conferenceStatus;
}
