package br.com.fiap.service;

import br.com.fiap.model.Game;
import br.com.fiap.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    private GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game save(Game jogo) {
        return gameRepository.save(jogo);
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}