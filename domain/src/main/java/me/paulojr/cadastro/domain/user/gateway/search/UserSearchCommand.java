package me.paulojr.cadastro.domain.user.gateway.search;

public record UserSearchCommand(
        String name,
        String email
) {
}
