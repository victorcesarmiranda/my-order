package com.example.myorder.api.dtos;

public class RestaurantResponseDto {


    private Integer id;
    private String nome;
    private String email;
    private String phone;

    public Integer getId() {
        return id;
    }

    public RestaurantResponseDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public RestaurantResponseDto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RestaurantResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RestaurantResponseDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
