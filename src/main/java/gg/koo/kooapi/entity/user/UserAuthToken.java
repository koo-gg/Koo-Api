package gg.koo.kooapi.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(orphanRemoval = true)
    private User user;

    @Column(length = 50)
    private String accessToken;

    private LocalDateTime expiresAt;

    @Column(length = 30)
    private String refreshToken;
}
