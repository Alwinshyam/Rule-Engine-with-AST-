package com.example.AST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.AST.model.Node;

@Repository
public interface RuleRepository extends JpaRepository<Node, Long> {
}
