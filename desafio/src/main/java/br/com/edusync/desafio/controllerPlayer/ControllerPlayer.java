package br.com.edusync.desafio.controllerPlayer;

import br.com.edusync.desafio.models.Modelsitens;
import br.com.edusync.desafio.models.Modelsplayer;
import br.com.edusync.desafio.service.Serviceitens;
import br.com.edusync.desafio.service.Serviceplayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController<PlayerNotFoundException> {

    private final Serviceplayer playerService;
    private final Serviceitens itemService;

    public PlayerController(Serviceplayer playerService, Serviceitens itemService) {
        this.playerService = playerService;
        this.itemService = itemService;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> addPlayer(@RequestBody Modelsplayer player, @RequestParam String itemName) {
        try {
            Modelsitens item = itemService.buscarPorArma(itemName);
            player.setItem(item);
            playerService.addPlayer(player);
            item.addPlayer(player);
            return new ResponseEntity<>(player, HttpStatus.CREATED);
        } catch (MissingArgumentException e) {
            return new ResponseEntity<>("Error: Missing arguments to create player", HttpStatus.BAD_REQUEST);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>("Error: Item not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Modelsplayer>> listAllPlayers() {
        List<Modelsplayer> players = playerService.listarTodosOsPlayers();
        if (players.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(players, HttpStatus.OK);
        }
    }

    @GetMapping("/{nickname}")
    public <PlayerNotFoundException extends Throwable> ResponseEntity<Modelsplayer> getPlayerByNickname(@PathVariable Integer nickname) {
        try {
            Modelsplayer player = playerService.buscarPorNickname(nickname);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{nickname}")
    public ResponseEntity<Modelsplayer> updatePlayer(@PathVariable Integer nickname, @RequestBody Modelsplayer player) {
        try {
            Modelsplayer updatedPlayer = playerService.alterarPlayer(nickname, player);
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{nickname}")
    public ResponseEntity<Modelsplayer> deletePlayer(@PathVariable Integer nickname) {
        try {
            Modelsplayer deletedPlayer = playerService.demitirJogador(nickname);
            return new ResponseEntity<>(deletedPlayer, HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}