package br.com.edusync.desafio.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Modelsitens {
    private String item;
    private String armas;
    private LocalDate recebido;
    private String breveDescricao;
    private List<Modelsplayer> modelsitens;
}
