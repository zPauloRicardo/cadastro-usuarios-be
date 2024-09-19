package me.paulojr.cadastro.domain.user.entity;

import me.paulojr.cadastro.domain.shared.domain.Identifier;

import java.util.Objects;

public class UserID extends Identifier<Long> {

    private final Long id;

    public UserID(Long id) {
        this.id = Objects.requireNonNull(id, "ID inválido.");
    }




    @Override
    public Long getValue() {
        return this.id;
    }
}
