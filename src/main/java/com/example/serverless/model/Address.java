package com.example.serverless.model;

public record Address(Integer id, String street, String suite, String city,
                      String zipcode) {
}