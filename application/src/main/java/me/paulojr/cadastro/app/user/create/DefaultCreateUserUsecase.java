package me.paulojr.cadastro.app.user.create;

import me.paulojr.cadastro.domain.shared.validation.handler.Notification;
import me.paulojr.cadastro.domain.user.entity.User;
import me.paulojr.cadastro.domain.user.gateway.UserGateway;

public class DefaultCreateUserUsecase extends CreateUserUsecase {


    private final UserGateway userGateway;

    public DefaultCreateUserUsecase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public CreateUserOutput execute(CreateUserCommand anIn) {
        final Notification notification = Notification.create();
        User user = User.createUser(anIn.name(), anIn.email(), anIn.telephone());
        user.validate(notification);
        notification.throwIfHasError();
        user = this.userGateway.create(user);
        return new CreateUserOutput(user.getId().getValue(), user.getName(), user.getEmail(), user.getPhone());

    }
}
