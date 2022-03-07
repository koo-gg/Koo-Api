package gg.koo.kooapi.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @EmbeddedId
    private UserId userId;

    @Column(length = 35)
    private String username;

    @Column(length = 35)
    private String avartar;

    @Column(length = 4)
    private String discriminator;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
