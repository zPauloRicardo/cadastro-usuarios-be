package me.paulojr.cadastro.infra.context.user.repository;

import me.paulojr.cadastro.infra.context.user.entity.UserJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Page<UserJpaEntity> findAll(Specification<UserJpaEntity> where, Pageable pageable);
}
