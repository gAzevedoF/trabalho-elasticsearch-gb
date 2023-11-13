package com.elasticsearch.estudo.service;

import co.elastic.clients.elasticsearch.core.DeleteResponse;
import com.elasticsearch.estudo.domain.VariavelCredito;
import com.elasticsearch.estudo.repository.VariavelRepository;
import com.elasticsearch.estudo.response.ConsultaIndicesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariavelService {
    @Autowired
    private VariavelRepository variavelRepository;

    public ConsultaIndicesResponse consultarIndices() {
        ConsultaIndicesResponse consultaIndicesResponse = new ConsultaIndicesResponse();
        consultaIndicesResponse.setIndices(this.variavelRepository.consultarIndices());

        return consultaIndicesResponse;
    }
    public void criarIndice(String nomeIndice) {
        this.variavelRepository.criarIndice(nomeIndice);
    }

    public void deletarIndice(String nomeIndice) {
        this.variavelRepository.deletarIndice(nomeIndice);
    }
    public void inserirVariavel(String nuCpfCnpj, String id, VariavelCredito variavelCredito) {
        this.variavelRepository.inserirVariavel(nuCpfCnpj, id, variavelCredito);
    }
    public VariavelCredito getVariavel(String nuCpfCnpj, String variavelId) {
        return variavelRepository.consultarVariavel(nuCpfCnpj, variavelId);
    }

    public List<VariavelCredito> consultarTodasVariaveis(String nuCpfCnpj) {
        return variavelRepository.consultarTodasVariavel(nuCpfCnpj);
    }
    public DeleteResponse deletarVariavel(String nuCpfCnpj, String id) {
        return variavelRepository.deletarVariavel(nuCpfCnpj, id);
    }
}