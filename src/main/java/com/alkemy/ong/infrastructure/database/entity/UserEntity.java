package com.alkemy.ong.infrastructure.database.entity;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "USERS")
@SQLDelete(sql = "UPDATE user SET soft_deleted = true WHERE user_id = ?")
public class UserEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long userId;


  @Column(name = "FIRST_NAME", nullable = false)
  private String firstName;


  @Column(name = "LAST_NAME", nullable = false)
  private String lastName;


  @Column(name = "EMAIL", nullable = false, unique = true)
  private String email;


  @Column(name = "PASSWORD", nullable = false)
  private String password;


  @Column(name = "IMAGE_URL")
  private String imageUrl;


  @OneToOne(fetch = LAZY, cascade = MERGE)
  @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
  private RoleEntity role;


  @Column(name = "CREATE_TIMESTAMP", updatable = false)
  @CreationTimestamp
  private Timestamp createTimestamp;


  @Column(name = "SOFT_DELETED")
  private boolean softDeleted;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserEntity that = (UserEntity) o;
    return userId.equals(that.userId) && email.equals(that.email);
  }


  @Override
  public int hashCode() {
    return Objects.hash(userId, email);
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.getName()));
  }


  @Override
  public String getUsername() {
    return this.email;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }


  @Override
  public boolean isAccountNonLocked() {
    return true;
  }


  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }


  @Override
  public boolean isEnabled() {
    return !this.softDeleted;
  }

}
