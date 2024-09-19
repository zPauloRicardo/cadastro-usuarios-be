package me.paulojr.cadastro.app.user.search;

public record SearchUserCommand(
        String name,
        String email,
        Integer page
) {
}
