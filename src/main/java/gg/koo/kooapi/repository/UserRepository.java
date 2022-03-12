package gg.koo.kooapi.repository;

import gg.koo.kooapi.entity.user.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends R2dbcRepository<User, String> {
}
