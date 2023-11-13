package com.elasticsearch.estudo.controller;

import co.elastic.clients.elasticsearch.core.DeleteResponse;
import com.elasticsearch.estudo.domain.VariavelCredito;
import com.elasticsearch.estudo.response.ConsultaIndicesResponse;
import com.elasticsearch.estudo.request.ConsultarVariavelRepresentation;
import com.elasticsearch.estudo.request.IndiceRepresentation;
import com.elasticsearch.estudo.request.VariavelCreditoRepresentation;
import com.elasticsearch.estudo.response.ConsultaVariavelResponse;
import com.elasticsearch.estudo.service.VariavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class VariavelController {

    @Autowired
    private VariavelService variavelService;

    @GetMapping(value="/consultarIndices")
    public ResponseEntity<Object> consultarIndices() {
        ConsultaIndicesResponse indicesEncontrados = variavelService.consultarIndices();

        if (indicesEncontrados.getIndices().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum indice encontrado");
        } else {
            return ResponseEntity.ok(indicesEncontrados);
        }
    }

    @PostMapping(value="/criarIndice")
    public ResponseEntity criarIndice(@RequestBody IndiceRepresentation indiceRepresentation) {
        variavelService.criarIndice(indiceRepresentation.getNomeIndice());

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deletarIndice")
    public ResponseEntity deletarIndice(@RequestBody IndiceRepresentation indiceRepresentation) {
        variavelService.deletarIndice(indiceRepresentation.getNomeIndice());

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/inserirVariavel")
    public ResponseEntity inserirVariavel(@RequestBody VariavelCreditoRepresentation variavelCreditoRepresentation) {
        String nuCpfCnpj = variavelCreditoRepresentation.getNuCpfCnpj();
        VariavelCredito variavel = variavelCreditoRepresentation.getVariavelCredito();
        String id = variavelCreditoRepresentation.getId();

        variavelService.inserirVariavel(nuCpfCnpj, id, variavel);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value="/consultarVariavel")
    public ResponseEntity<ConsultaVariavelResponse> consultaVariavel(@RequestBody ConsultarVariavelRepresentation consultarVariavelRepresentation) {
        VariavelCredito variavelCredito =
                variavelService.getVariavel(
                        consultarVariavelRepresentation.getNumeroCpfCnpj(),
                        consultarVariavelRepresentation.getId()
                );

        if(variavelCredito == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ConsultaVariavelResponse.builder().resultado("Variavel não encontrada").build());
        }

        return ResponseEntity.ok(ConsultaVariavelResponse.builder().resultado("Variavel encontrada").variavelCredito(variavelCredito).build());
    }

    @GetMapping(value="/consultarTodasVariaveis")
    public ResponseEntity<Object> consultarTodasVariaveis(@RequestBody ConsultarVariavelRepresentation consultarVariavelRepresentation) {
        List<VariavelCredito> variaveis =
                variavelService.consultarTodasVariaveis(
                        consultarVariavelRepresentation.getNumeroCpfCnpj()
                );

        if(variaveis.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ConsultaVariavelResponse.builder().resultado("Nenhuma variavel encontrada").build());
        }

        return ResponseEntity.ok(Map.of("variaveis", variaveis));
    }

    @DeleteMapping(value="/deletarVariavel")
    public ResponseEntity<Object> deletarVariavel(@RequestBody ConsultarVariavelRepresentation consultarVariavelRepresentation) {
        DeleteResponse deleteResponse = variavelService.deletarVariavel(
                        consultarVariavelRepresentation.getNumeroCpfCnpj(),
                        consultarVariavelRepresentation.getId()
        );

        if (deleteResponse.result().jsonValue().equals("not_found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("resultado", "Variavel não encontrada"));
        } else {
            return ResponseEntity.ok(Map.of("resultado", "Variavel deletada com sucesso"));
        }
    }
}