package ru.job4j.control;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.service.PostService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {
    @Mock
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnPost() throws Exception {
        this.mockMvc.perform(get("/post?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnNewPost() throws Exception {
        this.mockMvc.perform(get("/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("new"));
    }

    @Test
    @WithMockUser
    public void shouldReturnEditPost() throws Exception {
        this.mockMvc.perform(get("/post/edit?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }
}