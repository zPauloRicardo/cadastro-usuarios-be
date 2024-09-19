package me.paulojr.cadastro.app.user.create;

public record CreateUserCommand(
        String name,
        String email,
        String telephone
) {
}
