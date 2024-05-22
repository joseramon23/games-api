package com.example.gameserviceapi.services.impl;

import com.example.gameserviceapi.commons.entities.Game;
import com.example.gameserviceapi.commons.exceptions.GameException;
import com.example.gameserviceapi.repositories.GameRepository;
import com.example.gameserviceapi.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game saveGame(String userId, Game gameRequest) {
        gameRequest.setUserId(Integer.parseInt(userId));
        return this.gameRepository.save(gameRequest);
    }

    @Override
    public Game getGameById(String id) {
        return this.gameRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Error finding game"));
    }

    @Override
    public ArrayList<Game> getGames() {
        return (ArrayList<Game>) this.gameRepository.findAll();
    }

    @Override
    public Game updateGame(String id, Game gameRequest) {
        Game existsGame = this.gameRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Error finding game"));

        existsGame.setName(gameRequest.getName());

        return this.gameRepository.save(existsGame);
    }

    @Override
    public String deleteGame(String id) {
        Game existsGame = this.gameRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Error finding game"));

        this.gameRepository.deleteById(Long.valueOf(id));
        return "Game ID: " + id + " Has been deleted";
    }
}
