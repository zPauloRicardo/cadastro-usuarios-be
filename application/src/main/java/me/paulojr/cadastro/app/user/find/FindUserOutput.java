package me.paulojr.cadastro.app.user.find;

public record FindUserOutput(
        Long id,
        String name,
        String email,
        String phone
) {
}
