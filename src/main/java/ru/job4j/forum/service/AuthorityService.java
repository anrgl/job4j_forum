package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.AuthorityRepository;

@Service
public class AuthorityService {
    private final AuthorityRepository authorities;

    public AuthorityService(AuthorityRepository authorities) {
        this.authorities = authorities;
    }

    public Authority findByAuthority(String authority) {
        return authorities.findByAuthority(authority);
    }
}
