package me.paulojr.cadastro.domain.user.entity;

import lombok.Getter;
import me.paulojr.cadastro.domain.shared.domain.AggregateRoot;
import me.paulojr.cadastro.domain.shared.validation.ValidationHandler;
import me.paulojr.cadastro.domain.user.validation.UserValidator;

import java.util.Objects;

@Getter
public class User extends AggregateRoot<UserID> {

    private String name;
    private String email;
    private String telephone;

    protected User(UserID userID, String name, String email, String telephone) {
        super(userID);
        this.name = Objects.requireNonNull(name, "Nome deve ser informado");
        this.email = Objects.requireNonNull(email, "Email deve ser informado");
        this.telephone = Objects.requireNonNull(telephone, "Telefone deve ser informado");
    }

    public static User createUser(String name, String email, String telephone) {
        return new User(null, name, email, telephone);
    }

    public static User with(UserID id, String name, String email, String telephone) {
        return new User(id, name, email, telephone);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new UserValidator(handler, this);
    }
}
