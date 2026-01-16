package uvt.tw.conferencemanagementsystem.app.user.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uvt.tw.conferencemanagementsystem.api.UserApi;
import uvt.tw.conferencemanagementsystem.api.dto.user.CreateUserRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.CurrentUserResponseDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.UpdateUserDataRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.UserResponseDto;
import uvt.tw.conferencemanagementsystem.app.user.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;

  @Override
  public ResponseEntity<UserResponseDto> createUser(CreateUserRequestDto createUserRequestDto) {
    log.info("Received request for createUser with {}", createUserRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(userService.createUser(createUserRequestDto));
  }

  @Override
  public ResponseEntity<UserResponseDto> updateUser(
      UpdateUserDataRequestDto updateUserRequestDto, Long userId) {
    log.info("Received request for updateUser with id: {} and {}", userId, updateUserRequestDto);

    return ResponseEntity.status(HttpStatus.OK)
        .body(userService.updateUser(updateUserRequestDto, userId));
  }

  @Override
  public ResponseEntity<List<UserResponseDto>> getAllUsers() {
    log.info("Received request for getAllUsers");

    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  @Override
  public ResponseEntity<UserResponseDto> getUserById(Long userId) {
    log.info("Received request for getUserById with id: {}", userId);
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
  }

  @Override
  public ResponseEntity<CurrentUserResponseDto> getCurrentUser() {
    log.info("Received request for getCurrentUser");

    return ResponseEntity.status(HttpStatus.OK).body(userService.getCurrentUser());
  }

  @Override
  public ResponseEntity<Void> deleteUser(Long userId) {
    log.info("Received request for deleteUser with id: {}", userId);

    userService.deleteUserById(userId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
