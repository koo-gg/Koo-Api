package gg.koo.kooapi.repository;

import gg.koo.kooapi.entity.user.Authority;
import gg.koo.kooapi.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataR2dbcTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveTest() {

        String username = "sa46lll";

        Set<Authority> authority = (Set<Authority>) Authority.builder()
                .authorityName("USER")
                .build();

        User user = User.builder()
                .username(username)
                .avatar("avatar")
                .discriminator("dis")
                .authorities(authority)
                .build();

        // when
        User saved = userRepository.save(user)
                .block();

        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getUsername()).isEqualTo(username);
    }

}