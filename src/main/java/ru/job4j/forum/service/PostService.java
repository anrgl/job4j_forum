package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(Post.of("Продаю машину ладу 01."));
    }

    public List<Post> getAll() {
        return posts;
    }

    public Optional<Post> findById(int id) {
        try {
            return Optional.of(posts.get(id - 1));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public void save(Post post) {
        posts.add(post);
    }

    public void update(int id, Post post) {
        posts.get(id - 1).setName(post.getName());
        posts.get(id - 1).setDescription(post.getDescription());
    }
}
