package br.com.edusync.desafio.service;

import br.com.edusync.desafio.models.Modelsplayer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Serviceplayer {
    private List<Modelsplayer> players = new ArrayList<>();

    public void addPlayer(Modelsplayer player){
        players.add(player);
    }

    public List<Modelsplayer> listarTodosOsPlayers() {
        return players;
    }

    public boolean verificarSeListaVazia(){
        return players.isEmpty();
    }

    public Modelsplayer buscarPorNickname(Integer nickname){
        return players.stream().filter(p -> nickname.equals(p.getNikname())).findFirst().orElse(null);
    }

    public Modelsplayer alterarPlayer(Integer nicknameAntigo, Modelsplayer novoPlayer) {
        Modelsplayer playerAntigo = buscarPorNickname(nicknameAntigo);
        if (playerAntigo != null){
            players.remove(playerAntigo);
        }
        players.add(novoPlayer);
        return playerAntigo;
    }

    public Modelsplayer demitirJogador(Integer cpf) {
        Modelsplayer jogador = buscarPorNickname(cpf);
        if (jogador != null){
            players.remove(jogador);
        }
        return jogador;
    }
}


