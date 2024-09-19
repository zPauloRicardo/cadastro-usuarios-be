package me.paulojr.cadastro.app.user.update;

public record UpdateUserOutput(
        Long id,
        String name,
        String email,
        String phone
) {
}
