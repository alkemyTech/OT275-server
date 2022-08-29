package com.alkemy.ong.infrastructure.config.spring.security.common;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.IOperationAllowed;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDeleteOperationAllowed implements IOperationAllowed {

  private final ICommentRepository commentRepository;

  @Override
  public boolean isAuthorized(Identifiable<Long> identifiable) {
    Optional<Comment> comment = commentRepository.find(identifiable);

    Authentication authentication = getAuthentication();

    return comment.isPresent() && (isAdmin(authentication)
        || isUserCreator(comment.get().getCreatedBy(), authentication.getName()));
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  private boolean isUserCreator(User owner, String user) {
    return owner.getEmail().equals(user);
  }

  private boolean isAdmin(Authentication authentication) {
    return authentication.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .anyMatch(authority -> authority.equals(Role.ADMIN.getFullRoleName()));
  }

}