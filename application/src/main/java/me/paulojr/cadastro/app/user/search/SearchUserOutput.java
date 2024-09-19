package me.paulojr.cadastro.app.user.search;

public record SearchUserOutput(
        Long id,
        String name,
        String email,
        String phone
) {
}
