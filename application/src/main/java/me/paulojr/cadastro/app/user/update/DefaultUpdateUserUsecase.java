package me.paulojr.cadastro.app.user.update;

import me.paulojr.cadastro.domain.shared.exceptions.NotFoundException;
import me.paulojr.cadastro.domain.shared.validation.handler.Notification;
import me.paulojr.cadastro.domain.user.entity.User;
import me.paulojr.cadastro.domain.user.entity.UserID;
import me.paulojr.cadastro.domain.user.gateway.UserGateway;

public class DefaultUpdateUserUsecase extends UpdateUserUsecase {


    private final UserGateway userGateway;

    public DefaultUpdateUserUsecase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UpdateUserOutput execute(UpdateUserCommand anIn) {
        final Notification notification = Notification.create();
        final UserID userID = new UserID(anIn.id());
        User user = this.userGateway.findById(userID).orElseThrow(() -> NotFoundException.with(User.class, userID));
        user.update(anIn.name(), anIn.email(), anIn.telephone());
        user.validate(notification);
        notification.throwIfHasError();
        user = this.userGateway.update(user);
        return new UpdateUserOutput(user.getId().getValue(), user.getName(), user.getEmail(), user.getPhone());

    }
}
