package com.example.serverless.service;

import com.example.serverless.model.User;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface JsonPlaceholderService {

    @GetExchange("/users")
    List<User> loadPosts();
}
