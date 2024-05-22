package com.example.gameserviceapi.services;

import com.example.gameserviceapi.commons.entities.Game;

import java.util.ArrayList;

public interface GameService {
    Game saveGame(String userId, Game gameRequest);

    Game getGameById(String id);

    ArrayList<Game> getGames();

    Game updateGame(String id, Game gameRequest);

    String deleteGame(String id);
}
