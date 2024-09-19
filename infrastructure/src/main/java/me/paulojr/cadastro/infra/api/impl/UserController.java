package me.paulojr.cadastro.infra.api.impl;

import lombok.RequiredArgsConstructor;
import me.paulojr.cadastro.app.user.create.CreateUserCommand;
import me.paulojr.cadastro.app.user.create.CreateUserUsecase;
import me.paulojr.cadastro.app.user.find.FindUserCommand;
import me.paulojr.cadastro.app.user.find.FindUserUsecase;
import me.paulojr.cadastro.app.user.search.SearchUserCommand;
import me.paulojr.cadastro.app.user.search.SearchUserUsecase;
import me.paulojr.cadastro.app.user.update.UpdateUserCommand;
import me.paulojr.cadastro.app.user.update.UpdateUserUsecase;
import me.paulojr.cadastro.domain.shared.search.Pagination;
import me.paulojr.cadastro.infra.api.UserAPI;
import me.paulojr.cadastro.infra.context.user.models.UserRequestBody;
import me.paulojr.cadastro.infra.context.user.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController implements UserAPI {

    private final CreateUserUsecase createUserUsecase;
    private final UpdateUserUsecase updateUserUsecase;
    private final FindUserUsecase findUserUsecase;
    private final SearchUserUsecase searchUserUsecase;

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequestBody body) {

        final var execute = this.createUserUsecase.execute(
                new CreateUserCommand(body.name(), body.email(), body.phone())
        );

        final var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(execute.id())
                .toUri();


        return ResponseEntity.created(uri).body(new UserResponse(execute.id(), execute.name(), execute.email(), execute.phone()));
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long id, UserRequestBody body) {

        final var execute = this.updateUserUsecase.execute(
                new UpdateUserCommand(id, body.name(), body.email(), body.phone())
        );

        return ResponseEntity.ok(new UserResponse(execute.id(), execute.name(), execute.email(), execute.phone()));
    }

    @Override
    public ResponseEntity<UserResponse> findUser(Long id) {
        final var execute = this.findUserUsecase.execute(
                new FindUserCommand(id)
        );

        return ResponseEntity.ok(new UserResponse(execute.id(), execute.name(), execute.email(), execute.phone()));
    }

    @Override
    public ResponseEntity<Pagination<UserResponse>> search(String name, String email, Integer page) {
        final var execute = this.searchUserUsecase.execute(
                new SearchUserCommand(name, email, page)
        );
        return ResponseEntity.ok(execute.map(o -> new UserResponse(o.id(), o.name(), o.email(), o.phone())));
    }
}
