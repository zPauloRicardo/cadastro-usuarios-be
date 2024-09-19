package me.paulojr.cadastro.app.user.search;

import me.paulojr.cadastro.app.UseCase;
import me.paulojr.cadastro.domain.shared.search.Pagination;

public abstract class SearchUserUsecase extends UseCase<SearchUserCommand, Pagination<SearchUserOutput>> {
}
