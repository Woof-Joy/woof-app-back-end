package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.entity.object.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
