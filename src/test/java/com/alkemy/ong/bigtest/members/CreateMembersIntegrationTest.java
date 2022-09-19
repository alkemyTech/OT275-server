package com.alkemy.ong.bigtest.members;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.builder.CreateMembersRequestBuilder;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateMembersIntegrationTest extends BigTest {

  private static final String URL = "/members";

  private static final String NAME = "member";

  private static final String INSTAGRAM_URL = "instagram.com/member";

  private static final String FACEBOOK_URL = "facebook.com/member";

  private static final String LINKEDIN_URL = "linkedin.com/member";

  private static final String DESCRIPTION = "member description";

  private static final String IMAGE_URL = "member.jpg";

  @Test
  public void shouldReturnForbiddenWhenUserHasAdminRole() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME, IMAGE_URL, DESCRIPTION,
                FACEBOOK_URL, LINKEDIN_URL, INSTAGRAM_URL))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldCreateMemberWhenUserHasStandardRole() throws Exception {
    String memberResponse = mockMvc.perform(post(URL)
            .content(buildRequest(NAME, IMAGE_URL, DESCRIPTION,
                FACEBOOK_URL, LINKEDIN_URL, INSTAGRAM_URL))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.imageUrl", equalTo(IMAGE_URL)))
        .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)))
        .andExpect(jsonPath("$.socialMedia.facebookUrl", equalTo(FACEBOOK_URL)))
        .andExpect(jsonPath("$.socialMedia.linkedInUrl", equalTo(LINKEDIN_URL)))
        .andExpect(jsonPath("$.socialMedia.instagramUrl", equalTo(INSTAGRAM_URL)))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    Integer memberId= JsonPath.read(memberResponse, "$.memberId");
    assertMemberHasBeenCreated(Long.valueOf(memberId));
  }

  @Test
  public void shouldReturnBadRequestWhenNameHasNumbers() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("1",  IMAGE_URL, DESCRIPTION,
                FACEBOOK_URL, LINKEDIN_URL, INSTAGRAM_URL))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("",  IMAGE_URL, DESCRIPTION,
                FACEBOOK_URL, LINKEDIN_URL, INSTAGRAM_URL))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Name cannot be empty.")))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenImageHasWhiteSpace() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME, "x x", DESCRIPTION,
                FACEBOOK_URL, LINKEDIN_URL, INSTAGRAM_URL))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Image must be alphanumeric "
                + "without white spaces.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmpty() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME, "", DESCRIPTION,
                FACEBOOK_URL, LINKEDIN_URL, INSTAGRAM_URL))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Image cannot be empty.")))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Image must be alphanumeric "
                + "without white spaces.")))
        .andExpect(status().isBadRequest());
  }


  private String buildRequest(String name, String imageUrl, String description, String facebookUrl,
      String linkedinUrl, String instagramUrl) throws JsonProcessingException {
    return convert(
        CreateMembersRequestBuilder.buildRequest(name, imageUrl, description, facebookUrl,
            linkedinUrl, instagramUrl));
  }

  private void assertMemberHasBeenCreated(Long id){
    Optional<MemberEntity> memberEntity = memberRepository.findById(id);
    assertTrue(memberEntity.isPresent());
    assertThat(memberEntity.get().isSoftDeleted()).isFalse();
    assertEquals(NAME,memberEntity.get().getName());
    assertEquals(IMAGE_URL,memberEntity.get().getImageUrl());
    assertEquals(DESCRIPTION,memberEntity.get().getDescription());
    assertEquals(FACEBOOK_URL,memberEntity.get().getFacebookUrl());
    assertEquals(LINKEDIN_URL,memberEntity.get().getLinkedInUrl());
    assertEquals(INSTAGRAM_URL,memberEntity.get().getInstagramUrl());
  }
}