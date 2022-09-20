package com.alkemy.ong.bigtest.members;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class ListMembersIntegrationTest extends BigTest {

  private static final String URL = "/members";

  private static final String NAME = "member";

  private static final String INSTAGRAM_URL = "instagram.com/member";

  private static final String FACEBOOK_URL = "facebook.com/member";

  private static final String LINKEDIN_URL = "linkedin.com/member";

  private static final String DESCRIPTION = "member description";

  private static final String IMAGE_URL = "member.jpg";



  @Test
  public void shouldReturnForbiddenWhenUserHasAdminRole() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnEmptyPageWhenThereAreNoMembers() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.page", equalTo(0)))
        .andExpect(jsonPath("$.size", equalTo(10)))
        .andExpect(jsonPath("$.totalPages", equalTo(0)))
        .andExpect(jsonPath("$.members").value(empty()))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnPageWhenThereAreMembers() throws Exception {
   MemberEntity member = createMember(NAME,FACEBOOK_URL,INSTAGRAM_URL,LINKEDIN_URL, IMAGE_URL,DESCRIPTION);
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.page", equalTo(0)))
        .andExpect(jsonPath("$.size", equalTo(10)))
        .andExpect(jsonPath("$.totalPages", equalTo(1)))
        .andExpect(jsonPath("$.members[0].memberId").value(member.getMemberId()))
        .andExpect(jsonPath("$.members[0].name").value(NAME))
        .andExpect(jsonPath("$.members[0].socialMedia.facebookUrl").value(FACEBOOK_URL))
        .andExpect(jsonPath("$.members[0].socialMedia.linkedInUrl").value(LINKEDIN_URL))
        .andExpect(jsonPath("$.members[0].socialMedia.instagramUrl").value(INSTAGRAM_URL))
        .andExpect(jsonPath("$.members[0].imageUrl").value(IMAGE_URL))
        .andExpect(jsonPath("$.members[0].description").value(DESCRIPTION))
        .andExpect(status().isOk());
  }
}


