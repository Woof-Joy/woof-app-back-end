package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.entity.object.Dog;

public interface DogRepository extends JpaRepository<Dog, Integer> {

}
