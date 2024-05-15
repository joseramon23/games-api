package com.example.gameserviceapi.controller;

import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.services.GameService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        Game gameCreated = this.gameService.saveGame(game);
        return ResponseEntity.ok(gameCreated);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Game>> getGames() {
        ArrayList<Game> getGames = this.gameService.getGames();
        return ResponseEntity.ok(getGames);
    }

    public ResponseEntity<Game> getGameById(@RequestBody Long id) {
        Game gameById = this.gameService.getGameId(id);
        return ResponseEntity.ok(gameById);
    }
    /*
    @PutMapping
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        Game updatedGame = this.gameService.updateGame(game);
        return ResponseEntity.ok(updatedGame);
    }
    */
    @DeleteMapping
    public ResponseEntity<String> deleteGame(@RequestBody Long id) {
        String deletedGame = this.gameService.deleteGame(id);
        return ResponseEntity.ok(deletedGame);
    }
}
