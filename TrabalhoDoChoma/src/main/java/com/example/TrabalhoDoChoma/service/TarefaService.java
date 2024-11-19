package com.example.TrabalhoDoChoma.service;

import com.example.TrabalhoDoChoma.model.Tarefa;
import com.example.TrabalhoDoChoma.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> buscarTodos() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> buscarPorStatus(String status) {
        return tarefaRepository.findByStatus(status);
    }


    public Tarefa inserirTarefa(Tarefa tarefa) {
        if (tarefa.getNome() == null || tarefa.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório!");
        }
        tarefa.setStatus("A fazer");
        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(int id) {
        buscarId(id);
        tarefaRepository.deleteById(id);
    }

    public Tarefa editarTarefa(int id, Tarefa novosDados) {
        Tarefa tExistente = buscarId(id);

        if (novosDados.getNome() != null) tExistente.setNome(novosDados.getNome());
        if (novosDados.getDescricao() != null) tExistente.setDescricao(novosDados.getDescricao());
        if (novosDados.getPrioridade() != null) tExistente.setPrioridade(novosDados.getPrioridade());
        if (novosDados.getDataLimite() != null) tExistente.setDataLimite(novosDados.getDataLimite());

        return tarefaRepository.save(tExistente);
    }


    public Tarefa buscarId(int id) {
        Optional<Tarefa> consulta = tarefaRepository.findById(id);
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }
    }

    public Tarefa alterarStatus(int id) {
        Tarefa tInteresse = buscarId(id);

        switch (tInteresse.getStatus()) {
            case "A fazer":
                tInteresse.setStatus("Fazendo");
                break;
            case "Fazendo":
                tInteresse.setStatus("Concluído");
                break;
            case "Concluído":
                throw new IllegalStateException("Tarefa já está concluída e não pode ser movida.");
            default:
                throw new IllegalArgumentException("Status inválido: " + tInteresse.getStatus());
        }

        return tarefaRepository.save(tInteresse);
    }

}
