package me.paulojr.cadastro.infra.context.user.models;

import lombok.Getter;
import me.paulojr.cadastro.domain.shared.exceptions.InternalErrorException;

@Getter
public class UserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String phone;


    public UserResponse(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = formatPhoneNumber(phone);
    }

    private static String formatPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("\\D", "");
        if (phoneNumber.length() == 11) {
            return String.format("(%s) %s-%s",
                    phoneNumber.substring(0, 2),
                    phoneNumber.substring(2, 7),
                    phoneNumber.substring(7));
        } else if (phoneNumber.length() == 10) {
            return String.format("(%s) %s-%s",
                    phoneNumber.substring(0, 2),
                    phoneNumber.substring(2, 6),
                    phoneNumber.substring(6));
        } else {
            throw InternalErrorException.with("O número deve ter 10 ou 11 dígitos.", null);
        }
    }
}
