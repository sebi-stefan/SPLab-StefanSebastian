package uvt.tw.conferencemanagementsystem.app.security;

import java.util.Collection;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Builder
public class CustomUserDetails implements UserDetails {

  private Long id;
  private String username;
  private String password;
  private List<String> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (roles.isEmpty()) {
      return List.of();
    }
    return roles.stream().map(SimpleGrantedAuthority::new).toList();
  }
}
