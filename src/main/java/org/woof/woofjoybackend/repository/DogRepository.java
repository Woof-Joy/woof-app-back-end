package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.domain.entity.Dog;

import java.util.List;


public interface DogRepository extends JpaRepository<Dog, Integer> {
    List<Dog> findAllByFkDonoUsuarioId(Integer fkDonoId);

}
