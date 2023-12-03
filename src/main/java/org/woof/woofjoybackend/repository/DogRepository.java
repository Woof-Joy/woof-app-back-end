package org.woof.woofjoybackend.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.woof.woofjoybackend.entity.Dog;

import java.util.List;


public interface DogRepository extends JpaRepository<Dog, Integer> {
    List<Dog> findAllByFkDonoUsuarioId(Integer fkDonoId);

}
