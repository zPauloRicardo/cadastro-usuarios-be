package me.paulojr.cadastro.infra.context.user.models;


public record UserRequestBody(
        String name,
        String email,
        String phone
) {
}
