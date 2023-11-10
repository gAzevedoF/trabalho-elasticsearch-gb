package com.elasticsearch.estudo.controller;

import co.elastic.clients.elasticsearch.core.DeleteResponse;
import com.elasticsearch.estudo.domain.VariavelCredito;
import com.elasticsearch.estudo.representation.ConsultarVariavelRepresentation;
import com.elasticsearch.estudo.representation.IndiceRepresentation;
import com.elasticsearch.estudo.representation.VariavelCreditoRepresentation;
import com.elasticsearch.estudo.service.VariavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VariavelController {

    @Autowired
    private VariavelService variavelService;

    @PostMapping(value="/criarIndice")
    public ResponseEntity criarIndice(@RequestBody IndiceRepresentation indiceRepresentation) {
        variavelService.criarIndice(indiceRepresentation.getNomeIndice());

        return new ResponseEntity(HttpStatus.CREATED);
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
    public ResponseEntity<VariavelCredito> consultaVariavel(@RequestBody ConsultarVariavelRepresentation consultarVariavelRepresentation) {
        VariavelCredito variavelCredito =
                variavelService.getVariavel(
                        consultarVariavelRepresentation.getNuCpfCnpj(),
                        consultarVariavelRepresentation.getId()
                );

        return ResponseEntity.ok(variavelCredito);
    }

    @DeleteMapping(value="/deletarVariavel")
    public ResponseEntity<String> deletarVariavel(@RequestBody ConsultarVariavelRepresentation consultarVariavelRepresentation) {
        DeleteResponse deleteResponse = variavelService.deletarVariavel(
                        consultarVariavelRepresentation.getNuCpfCnpj(),
                        consultarVariavelRepresentation.getId()
        );

        if (deleteResponse.result().jsonValue().equals("not_found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Variavel não encontrada");
        } else {
            return ResponseEntity.ok(deleteResponse.toString());
        }
    }
}