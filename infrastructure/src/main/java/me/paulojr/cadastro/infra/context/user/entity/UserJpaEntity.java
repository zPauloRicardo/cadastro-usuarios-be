package me.paulojr.cadastro.infra.context.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.paulojr.cadastro.domain.user.entity.User;
import me.paulojr.cadastro.domain.user.entity.UserID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;


    public static UserJpaEntity fromDomain(User user) {
        return new UserJpaEntity(
                user.getId() != null ? user.getId().getValue() : null,
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }


    public User toDomain() {
        return User.with(new UserID(this.id), this.name, this.email, this.phone);
    }

}
