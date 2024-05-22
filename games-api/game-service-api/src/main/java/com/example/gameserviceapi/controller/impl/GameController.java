package com.example.gameserviceapi.controller.impl;

import com.example.gameserviceapi.commons.entities.Game;
import com.example.gameserviceapi.controller.GameApi;
import com.example.gameserviceapi.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class GameController implements GameApi {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<Game> saveGame(@RequestHeader("userIdRequest") String userId, @RequestBody Game game) {
        Game gameCreated = this.gameService.saveGame(userId, game);
        return ResponseEntity.ok(gameCreated);
    }

    @Override
    public ResponseEntity<Game> getGameById(String id) {
        return ResponseEntity.ok(this.gameService.getGameById(id));
    }

    @Override
    public ResponseEntity<ArrayList<Game>> getGames() {
        return ResponseEntity.ok(this.gameService.getGames());
    }

    @Override
    public ResponseEntity<Game> updateGame(String id, Game game) {
        return ResponseEntity.ok(this.gameService.updateGame(id, game));
    }

    @Override
    public ResponseEntity<String> deleteGame(String id) {
        return ResponseEntity.ok(this.gameService.deleteGame(id));
    }
}
