package uvt.tw.conferencemanagementsystem.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uvt.tw.conferencemanagementsystem.api.dto.user.CreateUserRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.CurrentUserResponseDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.UpdateUserDataRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.UserResponseDto;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
public interface UserApi {

  @PostMapping
  ResponseEntity<UserResponseDto> createUser(
      @RequestBody @Valid CreateUserRequestDto createUserRequestDto);

  @PostMapping("/{userId}")
  ResponseEntity<UserResponseDto> updateUser(
      @RequestBody @Valid UpdateUserDataRequestDto updateUserRequestDto, @PathVariable Long userId);

  @GetMapping
  ResponseEntity<List<UserResponseDto>> getAllUsers();

  @GetMapping("/{userId}")
  ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId);

  @GetMapping("/current")
  ResponseEntity<CurrentUserResponseDto> getCurrentUser();

  @DeleteMapping("/{userId}")
  ResponseEntity<Void> deleteUser(@PathVariable Long userId);
}
