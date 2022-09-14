package com.alkemy.ong.bigtest.comment;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.rest.request.comment.UpdateCommentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateCommentIntegrationTest extends BigTest {

  private static final String URL = "/comments/{id}";

  @Test
  public void shouldUpdateCommentWhenUserHasStandardRoleAndIsCreator() throws Exception {
    Long randomCommentId = getRandomCommentId();

    mockMvc.perform(put(URL, String.valueOf(randomCommentId))
            .content(getDefaultRequestBody())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.body", equalTo("My new comment body")))
        .andExpect(status().isOk());

    assertCommentHasBeenUpdated(randomCommentId);
  }

  private String getDefaultRequestBody() throws JsonProcessingException {
    UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest();
    updateCommentRequest.setBody("My new comment body");
    return convert(updateCommentRequest);
  }

  private void assertCommentHasBeenUpdated(Long commentId) {
    Optional<CommentEntity> commentEntity = commentRepository.findById(commentId);
    assertTrue(commentEntity.isPresent());
    assertEquals("My new comment body", commentEntity.get().getBody());
  }

}
