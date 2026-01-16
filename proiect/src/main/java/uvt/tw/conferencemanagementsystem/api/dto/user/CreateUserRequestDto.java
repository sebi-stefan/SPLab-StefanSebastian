package uvt.tw.conferencemanagementsystem.api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import uvt.tw.conferencemanagementsystem.api.dto.user.enums.UserRole;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateUserRequestDto extends UpdateUserDataRequestDto {

  @Size(min = 10, max = 30)
  @NotNull
  @Email
  private String email;

  @Size(min = 6, max = 100)
  @NotNull
  private String password;

  @NotNull private UserRole role;
}
