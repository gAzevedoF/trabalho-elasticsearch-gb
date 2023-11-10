package com.elasticsearch.estudo.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.elasticsearch.estudo.domain.VariavelCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class VariavelRepository {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public void criarIndice(String nomeIndice) {
        try {
            elasticsearchClient.indices().create(c -> c.index(nomeIndice));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void inserirVariavel(String nuCpfCnpj, String idVariavel, VariavelCredito variavelCredito) {
        String id = nuCpfCnpj + "#" + idVariavel;

        try {
            elasticsearchClient.index(i -> i.index("variaveis").id(id).document(variavelCredito));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VariavelCredito consultarVariavel(String nuCpfCnpj, String variavelId) {
        String id = nuCpfCnpj + "#" + variavelId;

        GetResponse<VariavelCredito> response = null;
        try {
            response = elasticsearchClient.get(g -> g.index("variaveis").id(id), VariavelCredito.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response.source();
    }

    public void atualizarVariavel(String nuCpfCnpj, String variavelId) {
        String id = nuCpfCnpj + "#" + variavelId;

        try {
            elasticsearchClient.delete(d -> d.index("variaveis").id(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public DeleteResponse deletarVariavel(String nuCpfCnpj, String variavelId) {
        String id = nuCpfCnpj + "#" + variavelId;

        try {
           return elasticsearchClient.delete(d -> d.index("variaveis").id(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
