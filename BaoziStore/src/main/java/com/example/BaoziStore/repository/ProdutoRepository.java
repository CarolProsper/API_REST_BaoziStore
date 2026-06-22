package com.example.BaoziStore.repository;

import com.example.BaoziStore.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}