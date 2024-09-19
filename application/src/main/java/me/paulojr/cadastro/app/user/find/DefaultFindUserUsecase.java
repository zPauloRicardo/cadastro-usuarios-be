package me.paulojr.cadastro.app.user.find;

import me.paulojr.cadastro.domain.shared.exceptions.NotFoundException;
import me.paulojr.cadastro.domain.user.entity.User;
import me.paulojr.cadastro.domain.user.entity.UserID;
import me.paulojr.cadastro.domain.user.gateway.UserGateway;

public class DefaultFindUserUsecase extends FindUserUsecase {


    private final UserGateway userGateway;

    public DefaultFindUserUsecase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public FindUserOutput execute(FindUserCommand anIn) {
        final UserID userID = new UserID(anIn.id());
        final User user = this.userGateway.findById(userID).orElseThrow(() -> NotFoundException.with(User.class, userID));
        return new FindUserOutput(user.getId().getValue(), user.getName(), user.getEmail(), user.getPhone());
    }
}
