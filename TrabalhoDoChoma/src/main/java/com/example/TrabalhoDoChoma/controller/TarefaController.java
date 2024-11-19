package com.example.TrabalhoDoChoma.controller;

import com.example.TrabalhoDoChoma.model.Tarefa;
import com.example.TrabalhoDoChoma.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        if (tarefa.getNome() == null || tarefa.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório!");
        }
        return tarefaService.inserirTarefa(tarefa);
    }

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Tarefa buscarTarefaPorId(@PathVariable int id) {
        return tarefaService.buscarId(id);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable int id) {
        tarefaService.excluirTarefa(id);
    }


    @PutMapping("/{id}")
    public Tarefa editarTarefa(@PathVariable int id, @RequestBody Tarefa novosDados) {
        return tarefaService.editarTarefa(id, novosDados);
    }


    @PutMapping("/{id}/move")
    public Tarefa moverTarefa(@PathVariable int id) {
        return tarefaService.alterarStatus(id);
    }

    @GetMapping("/status/{status}")
    public List<Tarefa> listarPorStatus(@PathVariable String status) {
        return tarefaService.buscarPorStatus(status);
    }

}

