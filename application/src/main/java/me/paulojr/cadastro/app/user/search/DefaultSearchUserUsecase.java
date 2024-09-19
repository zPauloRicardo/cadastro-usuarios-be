package me.paulojr.cadastro.app.user.search;

import me.paulojr.cadastro.domain.shared.search.Pagination;
import me.paulojr.cadastro.domain.user.gateway.UserGateway;
import me.paulojr.cadastro.domain.user.gateway.search.UserSearchCommand;

public class DefaultSearchUserUsecase extends SearchUserUsecase {


    private final UserGateway userGateway;

    public DefaultSearchUserUsecase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Pagination<SearchUserOutput> execute(SearchUserCommand anIn) {
        final var command = new UserSearchCommand(anIn.name(), anIn.email());
        return this.userGateway.findAll(command)
                .map(user -> new SearchUserOutput(
                        user.getId().getValue(),
                        user.getName(),
                        user.getEmail(),
                        user.getPhone()
                ));
    }
}
