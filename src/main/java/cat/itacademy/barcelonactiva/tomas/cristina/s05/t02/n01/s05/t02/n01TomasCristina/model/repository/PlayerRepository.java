package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.repository;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.domain.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer>{
    boolean existsByEmail(String email);
    Optional<PlayerEntity> findByEmail(String email);
}
