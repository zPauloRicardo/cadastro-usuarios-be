package me.paulojr.cadastro.infra.context.user;

import lombok.RequiredArgsConstructor;
import me.paulojr.cadastro.domain.shared.search.Pagination;
import me.paulojr.cadastro.domain.user.entity.User;
import me.paulojr.cadastro.domain.user.entity.UserID;
import me.paulojr.cadastro.domain.user.gateway.UserGateway;
import me.paulojr.cadastro.domain.user.gateway.search.UserSearchCommand;
import me.paulojr.cadastro.infra.context.user.entity.UserJpaEntity;
import me.paulojr.cadastro.infra.context.user.repository.UserJpaRepository;
import me.paulojr.cadastro.infra.utils.SpecificationUtils;
import me.paulojr.cadastro.infra.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserJpaGateway implements UserGateway {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User update(User toUpdate) {
        return this.userJpaRepository.save(UserJpaEntity.fromDomain(toUpdate)).toDomain();
    }

    @Override
    public User create(User toCreate) {
        return this.userJpaRepository.save(UserJpaEntity.fromDomain(toCreate)).toDomain();
    }

    @Override
    public Optional<User> findById(UserID userID) {
        return this.userJpaRepository.findById(userID.getValue()).map(UserJpaEntity::toDomain);
    }

    @Override
    public Pagination<User> findAll(UserSearchCommand userSearchCommand) {
        final Pageable pageable = PageRequest.of(
                userSearchCommand.page(),
                10,
                Sort.by(Sort.Direction.DESC, "id")
        );

        final Specification<UserJpaEntity> specification = setupFilters(userSearchCommand);
        final Page<UserJpaEntity> pageResult = this.userJpaRepository.findAll(
                Specification.where(specification), pageable
        );


        return new Pagination<>(
                userSearchCommand.page(),
                pageable.getPageSize(),
                pageResult.getTotalElements(),
                pageResult.map(UserJpaEntity::toDomain).toList()
        );
    }


    private Specification<UserJpaEntity> setupFilters(UserSearchCommand aQuery) {
        Specification<UserJpaEntity> specifications = null;
        if (aQuery.name() != null) {
            specifications = SpecificationUtils.like("name", SqlUtils.like(aQuery.name()));
        }
        if (aQuery.email() != null) {
            Specification<UserJpaEntity> spec = SpecificationUtils.like("email", SqlUtils.like(aQuery.email()));
            specifications = specifications == null ? spec : specifications.and(spec);
        }

        return specifications;
    }
}
