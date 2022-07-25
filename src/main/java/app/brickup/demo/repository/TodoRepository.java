package app.brickup.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.brickup.demo.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
   
} 
     