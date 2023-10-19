package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.entity.Dog;

public interface DogRepository extends JpaRepository<Dog, Integer> {

}
