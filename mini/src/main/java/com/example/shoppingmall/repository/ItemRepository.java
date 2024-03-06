package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
