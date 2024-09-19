package me.paulojr.cadastro.domain.user.gateway;

import me.paulojr.cadastro.domain.shared.search.Pagination;
import me.paulojr.cadastro.domain.user.entity.User;
import me.paulojr.cadastro.domain.user.entity.UserID;
import me.paulojr.cadastro.domain.user.gateway.search.UserSearchCommand;

import java.util.Optional;

public interface UserGateway {

    User update(User toUpdate);

    User create(User toCreate);

    Optional<User> findById(UserID userID);

    Pagination<User> findAll(UserSearchCommand userSearchCommand);

}
