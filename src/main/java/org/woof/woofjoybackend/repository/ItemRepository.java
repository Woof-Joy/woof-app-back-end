package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
