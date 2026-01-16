package uvt.tw.conferencemanagementsystem.app.user.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uvt.tw.conferencemanagementsystem.api.dto.user.CreateUserRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.CurrentUserResponseDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.UpdateUserDataRequestDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.UserResponseDto;
import uvt.tw.conferencemanagementsystem.api.dto.user.enums.UserRole;
import uvt.tw.conferencemanagementsystem.api.exception.OwnershipException;
import uvt.tw.conferencemanagementsystem.api.exception.UserAlreadyExistsException;
import uvt.tw.conferencemanagementsystem.api.exception.UserNotFoundException;
import uvt.tw.conferencemanagementsystem.app.security.CustomUserDetails;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;
import uvt.tw.conferencemanagementsystem.app.user.repository.UserRepository;
import uvt.tw.conferencemanagementsystem.app.user.util.UserConverter;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public UserResponseDto createUser(CreateUserRequestDto requestDto) {
    if (userRepository.existsByEmail(requestDto.getEmail())) {
      throw new UserAlreadyExistsException("Email already in use: " + requestDto.getEmail());
    }

    UserEntity user = new UserEntity();
    updateUserData(user, requestDto);

    user.setEmail(requestDto.getEmail());
    user.setPasswordHash(passwordEncoder.encode(requestDto.getPassword()));
    user.setRole(UserRole.valueOf(requestDto.getRole().name()));

    UserEntity savedUser = userRepository.save(user);

    return UserConverter.convertToReponseDto(savedUser);
  }

  @Transactional
  public UserResponseDto updateUser(UpdateUserDataRequestDto updatedUserData, Long id) {
    UserEntity user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    validateUserOwnership(user);

    updateUserData(user, updatedUserData);

    UserEntity savedUser = userRepository.save(user);
    return UserConverter.convertToReponseDto(savedUser);
  }

  @Transactional(readOnly = true)
  public CurrentUserResponseDto getCurrentUser() {
    return UserConverter.convertToCurrentUserResponseDto(getCurrentUserEntity());
  }

  @Transactional(readOnly = true)
  public List<UserResponseDto> getAllUsers() {
    return userRepository.findAll().stream().map(UserConverter::convertToReponseDto).toList();
  }

  public void deleteUserById(Long userId) {
    UserEntity userEntity =
        userRepository
            .findById(userId)
            .orElseThrow(
                () ->
                    new UserNotFoundException(String.format("User not found with id: %d", userId)));
    userRepository.deleteById(userEntity.getId());
  }

  @Transactional(readOnly = true)
  public UserResponseDto getUserById(Long id) {
    UserEntity userEntity =
        userRepository
            .findById(id)
            .orElseThrow(
                () -> new UserNotFoundException("User not found based on the specified id"));
    return UserConverter.convertToReponseDto(userEntity);
  }

  @Transactional(readOnly = true)
  public UserResponseDto getUserByEmail(String email) {
    UserEntity userEntity =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () -> new UserNotFoundException("User not found based on the specified email"));
    return UserConverter.convertToReponseDto(userEntity);
  }

  public UserEntity getCurrentUserEntity() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

    return userRepository
        .findById(customUserDetails.getId())
        .orElseThrow(() -> new UserNotFoundException("User not found"));
  }

  private void validateUserOwnership(UserEntity user) {
    UserEntity currentUser = getCurrentUserEntity();
    if (!(currentUser.getId().equals(user.getId()) || UserRole.ADMIN.equals(currentUser.getRole())))
      throw new OwnershipException();
  }

  private void updateUserData(UserEntity user, UpdateUserDataRequestDto requestDto) {
    user.setFirstName(requestDto.getFirstName());
    user.setLastName(requestDto.getLastName());
    user.setBio(requestDto.getBio());
    user.setOrganization(requestDto.getOrganization());
  }
}
