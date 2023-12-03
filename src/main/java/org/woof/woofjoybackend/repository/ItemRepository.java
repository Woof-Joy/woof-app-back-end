package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findAllByDonoId(Integer id);
}