package me.paulojr.cadastro.infra.config;

import lombok.RequiredArgsConstructor;
import me.paulojr.cadastro.app.user.create.CreateUserUsecase;
import me.paulojr.cadastro.app.user.create.DefaultCreateUserUsecase;
import me.paulojr.cadastro.app.user.find.DefaultFindUserUsecase;
import me.paulojr.cadastro.app.user.find.FindUserUsecase;
import me.paulojr.cadastro.app.user.search.DefaultSearchUserUsecase;
import me.paulojr.cadastro.app.user.search.SearchUserUsecase;
import me.paulojr.cadastro.app.user.update.DefaultUpdateUserUsecase;
import me.paulojr.cadastro.app.user.update.UpdateUserUsecase;
import me.paulojr.cadastro.domain.user.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UseCasesConfig {

    private final UserGateway userGateway;

    @Bean
    CreateUserUsecase createUserUsecase() {
        return new DefaultCreateUserUsecase(this.userGateway);
    }

    @Bean
    UpdateUserUsecase updateUserUsecase() {
        return new DefaultUpdateUserUsecase(this.userGateway);
    }

    @Bean
    FindUserUsecase findUserUsecase() {
        return new DefaultFindUserUsecase(this.userGateway);
    }

    @Bean
    SearchUserUsecase searchUserUsecase() {
        return new DefaultSearchUserUsecase(this.userGateway);
    }
}
