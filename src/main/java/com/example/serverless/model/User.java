package com.example.serverless.model;

public record User(Integer id, String name, String username, String email,
                   Address address) {
}
