package me.paulojr.cadastro.domain.user.validation;

import me.paulojr.cadastro.domain.shared.utils.ValidationUtils;
import me.paulojr.cadastro.domain.shared.validation.ValidationHandler;
import me.paulojr.cadastro.domain.shared.validation.Validator;
import me.paulojr.cadastro.domain.user.entity.User;

public class UserValidator extends Validator {

    private final User user;

    public UserValidator(ValidationHandler aHandler, User user) {
        super(aHandler);
        this.user = user;
    }

    @Override
    public void validate() {
        if(this.user.getName().isBlank()) {
            this.appendError("Nome não pode ser vazio.");
        }

        if(!ValidationUtils.isValidEmailAddress(this.user.getEmail())) {
            this.appendError("Email inválido.");
        }


        if (!ValidationUtils.isValidPhoneNumber(this.user.getTelephone()) &&
                !ValidationUtils.isValidMobilePhoneNumber(this.user.getTelephone())
        ) {
            this.appendError("Telefone inválido.");
        }

    }
}
