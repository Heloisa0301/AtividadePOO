package com.example.TrabalhoDoChoma.repository;

import com.example.TrabalhoDoChoma.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    List<Tarefa> findByStatus(String status);
}

