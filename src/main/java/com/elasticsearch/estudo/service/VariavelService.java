package com.elasticsearch.estudo.service;

import co.elastic.clients.elasticsearch.core.DeleteResponse;
import com.elasticsearch.estudo.domain.VariavelCredito;
import com.elasticsearch.estudo.repository.VariavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariavelService {
    @Autowired
    private VariavelRepository variavelRepository;
    public void criarIndice(String nomeIndice) {
        this.variavelRepository.criarIndice(nomeIndice);
    }
    public void inserirVariavel(String nuCpfCnpj, String id, VariavelCredito variavelCredito) {
        this.variavelRepository.inserirVariavel(nuCpfCnpj, id, variavelCredito);
    }
    public VariavelCredito getVariavel(String nuCpfCnpj, String variavelId) {
        return variavelRepository.consultarVariavel(nuCpfCnpj, variavelId);
    }
    public DeleteResponse deletarVariavel(String nuCpfCnpj, String id) {
        return variavelRepository.deletarVariavel(nuCpfCnpj, id);
    }
}