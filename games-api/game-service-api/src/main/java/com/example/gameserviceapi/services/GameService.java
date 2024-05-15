package com.example.gameserviceapi.services;

import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game saveGame(Game gameRequest) {
        Game gameCreatedInDatabase = this.gameRepository.save(gameRequest);
        return gameCreatedInDatabase;
    }

    public ArrayList<Game> getGames() {
        ArrayList<Game> gamesInDatabase = (ArrayList<Game>) this.gameRepository.findAll();
        return gamesInDatabase;
    }

    public Game getGameId(Long id) {
        Game gamesById = this.gameRepository.getReferenceById(id);
        return gamesById;
    }

    /*
    public Game updateGame(Game game) {
        Game updateGame = this.gameRepository.
    }
     */

    public String deleteGame(Long id) {
        this.gameRepository.deleteById(id);
        return "Game " + id + " deleted";
    }
}
