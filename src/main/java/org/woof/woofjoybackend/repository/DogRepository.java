package org.woof.woofjoybackend.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.woof.woofjoybackend.entity.Dog;

import java.util.List;


public interface DogRepository extends JpaRepository<Dog, Integer> {
    @Query("SELECT d FROM Dog d WHERE d.fkDono.id = :fkDonoId")
    List<Dog> findDogsByOwnerId(@Param("fkDonoId") Integer fkDonoId);

}
