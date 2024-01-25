package com.lucy.springboot.web;

import com.lucy.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트를 진행할 때 다른 실행자도 같이 실행시키는 메서드.
@WebMvcTest(controllers = HelloController.class,
  excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
  }
)
//@WebMvcTest(controllers = HelloController.class, secure = false) //
public class HelloControllerTest {

  @Autowired // 스프링이 관리하는 빈을 주입 받는다
  private MockMvc mvc; // 테스트 시작점 API 테스트하는 시작점

  @WithMockUser(roles="USER")
  @Test
  public void hello1() throws Exception {
    String hello = "hello";

    mvc.perform(get("/hello"))
      .andExpect(status().isOk())
      .andExpect(content().string(hello));
  }

  @Test
  @WithMockUser(roles="USER")
  public void helloDto() throws Exception {
    String name = "hello";
    int amount = 1000;

    mvc.perform(
        get("/hello/dto")
          .param("name", name)
          .param("amount", String.valueOf(amount)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is(name)))
      .andExpect(jsonPath("$.amount", is(amount)));
  }
}