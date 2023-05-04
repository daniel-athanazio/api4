package br.com.edusync.desafio.service;

import br.com.edusync.desafio.models.Modelsitens;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Serviceitens {
    private List<Modelsitens> Item = new ArrayList<>();

    public void novaArma(Modelsitens item){
        Item.add(item);
    }

    public List<Modelsitens> listarTodosOsItens() {
        return Item;
    }

    public Modelsitens buscarPorArma(String arma){
        return Item.stream().filter(p -> arma.equals(p.getArmas())).findFirst().orElse(null);
    }

    public void alterarItens(String armaAntiga, Modelsitens novoItem) {
        Modelsitens itemAntigo = buscarPorArma(armaAntiga);
        if (itemAntigo != null){
            Item.remove(itemAntigo);
        }
        Item.add(novoItem);
    }

    public void removerItem(String arma) {
        Modelsitens item = buscarPorArma(arma);
        if (item != null){
            Item.remove(item);
        }
    }
}





