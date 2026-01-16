package uvt.tw.conferencemanagementsystem.app.security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;
import uvt.tw.conferencemanagementsystem.app.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity userEntity = userRepository.findByEmail(username).orElseThrow();

    return CustomUserDetails.builder()
        .id(userEntity.getId())
        .username(userEntity.getEmail())
        .password(userEntity.getPasswordHash())
        .roles(List.of(userEntity.getRole().toString()))
        .build();
  }
}
