package com.alkemy.ong.bigtest.members;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.builder.CreateMembersRequestBuilder;
import com.alkemy.ong.builder.SocialMediaRequestBuilder;
import com.alkemy.ong.infrastructure.rest.request.common.SocialMediaRequest;
import com.alkemy.ong.infrastructure.rest.request.member.CreateMemberRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
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

  private String buildRequest(String name, String imageUrl, String description, String facebookUrl,
      String linkedinUrl, String instagramUrl) throws JsonProcessingException {
    return convert(
        CreateMembersRequestBuilder.buildRequest(name, imageUrl, description, facebookUrl,
            linkedinUrl, instagramUrl));
  }
}
