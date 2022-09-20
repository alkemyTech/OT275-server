package com.alkemy.ong.bigtest.members;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;

import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class DeleteMembersIntegrationTest extends BigTest {
  public static String URL = "/members/{id}/";

  @Test
  public void shouldReturnNotFoundWhenMemberDoesNotExist()throws Exception{
    mockMvc.perform(delete(URL,  "-1")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Member not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldDeleteMemberWhenUserHasAdminRole()throws Exception{
    Long memberId = getRandomMemberId();

    mockMvc.perform(delete(URL, String.valueOf(memberId))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(status().isNoContent());

    assertMemberHasBeenDeleted(memberId);
  }


  private void assertMemberHasBeenDeleted(Long randomMemberId) {
    Optional<MemberEntity> member = memberRepository.findById(randomMemberId);
    assertTrue(member.isPresent());
    assertTrue(member.get().isSoftDeleted());
  }
}
