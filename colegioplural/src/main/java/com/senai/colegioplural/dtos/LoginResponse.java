package com.senai.colegioplural.dtos;

public class LoginResponse {
    private String nome;
    private String perfil;
    private String email;
    private String token;

    public LoginResponse() {}

    public LoginResponse(String email, String nome, String perfil, String
    token) {
    this.email = email;
    this.nome = nome;
    this.perfil = perfil;
    this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
