package gg.koo.kooapi.entity.user;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @EmbeddedId
    private UserId userId;

    @Column(length = 35)
    private String username;

    @Column(length = 35)
    private String avatar;

    @Column(length = 4)
    private String discriminator;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
