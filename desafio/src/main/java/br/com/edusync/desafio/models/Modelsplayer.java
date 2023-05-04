package br.com.edusync.desafio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class Modelsplayer {

    private Modelsitens restaurante;
    private String nikname;
    private Integer nivel;
    private BigDecimal dano;
    private LocalDate entrada;
}
