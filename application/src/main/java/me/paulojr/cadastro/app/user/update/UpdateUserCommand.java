package me.paulojr.cadastro.app.user.update;

public record UpdateUserCommand(
        Long id,
        String name,
        String email,
        String telephone
) {
}
