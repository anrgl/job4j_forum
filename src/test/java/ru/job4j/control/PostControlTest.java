package ru.job4j.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {
    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnPost() throws Exception {
        when(posts.findById(1)).thenReturn(Optional.of(Post.of("New Post")));
        this.mockMvc.perform(get("/post?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldReturn404ForPost() throws Exception {
        this.mockMvc.perform(get("/post?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
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
        when(posts.findById(1)).thenReturn(Optional.of(Post.of("New Post")));
        this.mockMvc.perform(get("/post/edit?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturn404ForEditPost() throws Exception {
        this.mockMvc.perform(get("/post/edit?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/create")
                .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void shouldReturnUpdatedOkPost() throws Exception {
        when(posts.findById(1)).thenReturn(Optional.of(Post.of("New Post")));
        this.mockMvc.perform(post("/post/update?id=1")
                .param("name", "Updated Post"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Updated Post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnUpdatedFailPost() throws Exception {
        when(posts.findById(1)).thenReturn(Optional.empty());
        this.mockMvc.perform(post("/post/update?id=1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/post/edit?id=1"));
    }

    @Test
    @WithMockUser
    public void shouldDeletePost() throws Exception {
        this.mockMvc.perform(post("/post/delete?id=1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}
