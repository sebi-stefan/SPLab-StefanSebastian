package uvt.tw.conferencemanagementsystem.api.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

  private Long id;

  private String email;

  private String firstName;

  private String lastName;

  private String bio;

  private String organization;

  private String profilePictureUrl;
}
