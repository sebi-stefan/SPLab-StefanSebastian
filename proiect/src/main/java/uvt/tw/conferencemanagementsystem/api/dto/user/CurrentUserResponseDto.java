package uvt.tw.conferencemanagementsystem.api.dto.user;

import lombok.Builder;
import lombok.Data;
import uvt.tw.conferencemanagementsystem.api.dto.user.enums.UserRole;

@Data
@Builder
public class CurrentUserResponseDto {

  private Long id;
  private String email;
  private UserRole role;
  private String firstName;
  private String lastName;
  private String bio;
  private String organization;
  private String profilePictureUrl;
}
