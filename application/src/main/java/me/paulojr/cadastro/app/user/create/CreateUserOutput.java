package me.paulojr.cadastro.app.user.create;

public record CreateUserOutput(
        Long id,
        String name,
        String email,
        String phone
) {
}
