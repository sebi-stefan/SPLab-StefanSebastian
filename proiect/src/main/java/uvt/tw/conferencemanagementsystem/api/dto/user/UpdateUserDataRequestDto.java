package uvt.tw.conferencemanagementsystem.api.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UpdateUserDataRequestDto {

  @Size(min = 2, max = 20)
  @NotNull
  private String firstName;

  @Size(min = 2, max = 20)
  @NotNull
  private String lastName;

  @Size(max = 500)
  private String bio;

  @Size(max = 50)
  private String organization;
}
