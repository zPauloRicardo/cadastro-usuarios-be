package me.paulojr.cadastro.domain.user.entity;

import me.paulojr.cadastro.domain.shared.domain.Identifier;

public class UserID extends Identifier<Long> {

    private final Long id;

    public UserID(Long id) {
        this.id = id;
    }

    @Override
    public Long getValue() {
        return this.id;
    }
}
