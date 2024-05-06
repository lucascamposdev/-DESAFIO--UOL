package presto.com.Desafio.UOL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import presto.com.Desafio.UOL.domain.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
