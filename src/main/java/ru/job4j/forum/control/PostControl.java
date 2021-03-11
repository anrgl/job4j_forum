package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Optional;

@Controller
public class PostControl {
    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping("/post")
    public String show(@RequestParam int id, Model model) {
        Optional<Post> post = posts.findById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post";
        }
        return "404";
    }

    @GetMapping("/new")
    public String create() {
        return "new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post) {
        posts.save(post);
        return "redirect:/";
    }

    @GetMapping("/post/edit")
    public String edit(@RequestParam int id, Model model) {
        Optional<Post> post = posts.findById(id);
        if (post.isPresent()) {
            post.get().setId(id);
            model.addAttribute("post", post.get());
            return "edit";
        }
        return "404";
    }

    @PostMapping("/post/update")
    public String edit(@RequestParam int id, @ModelAttribute Post post) {
        posts.update(id, post);
        return "redirect:/post?id=" + id;
    }
}
