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
    private String phone;

    protected User(UserID userID, String name, String email, String phone) {
        super(userID);
        this.name = handleName(name);
        this.email = handleEmail(email);
        this.phone = handleTelephone(phone);
    }

    private static String handleName(String name) {
        return Objects.requireNonNull(name, "Nome deve ser informado");
    }

    private static String handleEmail(String email) {
        return Objects.requireNonNull(email, "Email deve ser informado");
    }

    private static String handleTelephone(String phone) {
        return Objects.requireNonNull(phone, "Telefone deve ser informado").replaceAll("[^0-9]", "");
    }



    public static User createUser(String name, String email, String phone) {
        return new User(null, name, email, phone);
    }

    public static User with(UserID id, String name, String email, String phone) {
        return new User(id, name, email, phone);
    }

    public void update(String name, String email, String phone) {
        this.name = handleName(name);
        this.email = handleEmail(email);
        this.phone = handleTelephone(phone);
    }


    @Override
    public void validate(ValidationHandler handler) {
        new UserValidator(handler, this);
    }
}
