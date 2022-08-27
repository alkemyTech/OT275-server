package com.alkemy.ong.infrastructure.config.spring.security.common;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.IAuthorization;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.rest.mapper.CommentMapper;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentAuthorization implements IAuthorization {

  private final ICommentRepository commentRepository;
  private final CommentMapper commentMapper;

  @Override
  public boolean isAuthorized(Identifiable<Long> identifiable) {
    Optional<Comment> comment = commentRepository.find(identifiable);
    Optional<CommentResponse> commentResponse = comment.map(commentMapper::toResponse);

    Authentication authentication = getAuthentication();

    return commentResponse.filter(
        response -> isAdmin(authentication) || isUserCreator(authentication, response)).isPresent();
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  private boolean isUserCreator(Authentication authentication, CommentResponse response) {
    return authentication.getName().equals(response.getUser().getEmail());
  }

  private boolean isAdmin(Authentication authentication) {
    return authentication.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .anyMatch(authority -> authority.equals(Role.ADMIN.name()));
  }

}