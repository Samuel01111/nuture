package br.com.nuture.model;

public record Token(
    String token,
    String type,
    String prefix
) {}