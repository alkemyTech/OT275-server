package com.alkemy.ong.bigtest;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.OngApplication;
import com.alkemy.ong.infrastructure.database.entity.FooEntity;
import com.alkemy.ong.infrastructure.database.repository.IFooSpringRepository;
import java.sql.Timestamp;
import java.time.Instant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "spring.main.allow-bean-definition-overriding=true",
    classes = OngApplication.class)
@AutoConfigureMockMvc
public class ListFooDetailsIntegrationTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected IFooSpringRepository fooSpringRepository;

  @Test
  public void shouldReturnOkWithListOfFoos() throws Exception {
    buildFooStubs();

    mockMvc.perform(get("/foos")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.foos", notNullValue()))
        .andExpect(jsonPath("$.foos[*].name").value(hasItem("my foo")))
        .andExpect(jsonPath("$.foos", hasSize(1)))
        .andExpect(status().isOk());
  }

  private void buildFooStubs() {
    FooEntity fooEntity = new FooEntity(1L, "my foo", Timestamp.from(Instant.now()), false);
    fooSpringRepository.save(fooEntity);
  }

}