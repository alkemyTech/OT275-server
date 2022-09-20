package com.alkemy.ong.bigtest.members;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.builder.SocialMediaRequestBuilder;
import com.alkemy.ong.infrastructure.rest.request.member.UpdateMemberRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateMembersIntegrationTest extends BigTest {

  private static final String URL = "/members/{id}";
  private static final String MEMBER_NAME = "Member name with spaces";
  private static final String MEMBER_IMAGE = "memberImageWithoutSpaces.jpg";
  private static final String MEMBER_DESCRIPTION = "Member description";
  private static final String MEMBER_FACEBOOK_URL = "facebook.com/updated_member";
  private static final String MEMBER_LINKED_IN_URL = "linkedin.com/updated_member";
  private static final String MEMBER_INSTAGRAM_URL = "instagram.com/updated_member";

  @Test
  public void shouldUpdateMemberWhenUserHasStandardRole() throws Exception {
    Long memberId = getRandomMemberId();

    mockMvc.perform(put(URL, String.valueOf(memberId))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(buildMemberUpdateRequest(MEMBER_NAME, MEMBER_IMAGE)))
        .andExpect(jsonPath("$.memberId", notNullValue()))
        .andExpect(jsonPath("$.name", equalTo(MEMBER_NAME)))
        .andExpect(jsonPath("$.imageUrl", equalTo(MEMBER_IMAGE)))
        .andExpect(jsonPath("$.description", equalTo(MEMBER_DESCRIPTION)))
        .andExpect(jsonPath("$.socialMedia.facebookUrl", equalTo(MEMBER_FACEBOOK_URL)))
        .andExpect(jsonPath("$.socialMedia.linkedInUrl", equalTo(MEMBER_LINKED_IN_URL)))
        .andExpect(jsonPath("$.socialMedia.instagramUrl", equalTo(MEMBER_INSTAGRAM_URL)))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty()
      throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(buildMemberUpdateRequest("", MEMBER_IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name cannot be empty.")))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }


  @Test
  public void shouldReturnBadRequestWhenNameHasANumber()
      throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(buildMemberUpdateRequest("Name with 1 number", MEMBER_IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmpty()
      throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(buildMemberUpdateRequest(MEMBER_NAME, ""))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Image cannot be empty.")))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Image must be alphanumeric without white spaces.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenImageHasSpaces()
      throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(buildMemberUpdateRequest(MEMBER_NAME, "member image with spaces.jpg"))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Image must be alphanumeric without white spaces.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasAdminRole() throws Exception {

    mockMvc.perform(put(URL, "1")
            .content(buildMemberUpdateRequest(MEMBER_NAME, MEMBER_IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnForbiddenWhenNoCredentialsAreProvided() throws Exception {

    mockMvc.perform(put(URL, "1")
            .content(buildMemberUpdateRequest(MEMBER_NAME, MEMBER_IMAGE))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  private String buildMemberUpdateRequest(
      String name,
      String image) throws JsonProcessingException {
    UpdateMemberRequest request = new UpdateMemberRequest();
    request.setName(name);
    request.setImage(image);
    request.setDescription(UpdateMembersIntegrationTest.MEMBER_DESCRIPTION);
    request.setSocialMedia(SocialMediaRequestBuilder.buildRequest(
        UpdateMembersIntegrationTest.MEMBER_FACEBOOK_URL,
        UpdateMembersIntegrationTest.MEMBER_LINKED_IN_URL,
        UpdateMembersIntegrationTest.MEMBER_INSTAGRAM_URL));
    return convert(request);
  }
}
