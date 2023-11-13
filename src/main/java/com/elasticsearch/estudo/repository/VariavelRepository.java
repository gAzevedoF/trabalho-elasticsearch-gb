package com.elasticsearch.estudo.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.elasticsearch.estudo.domain.VariavelCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class VariavelRepository {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public Set<String> consultarIndices() {
        try {
            return elasticsearchClient.indices().getAlias().result().keySet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void criarIndice(String nomeIndice) {
        try {
            elasticsearchClient.indices().create(c -> c.index(nomeIndice));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarIndice(String nomeIndice) {
        try {
            elasticsearchClient.indices().delete(c -> c.index(nomeIndice));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void inserirVariavel(String nuCpfCnpj, String idVariavel, VariavelCredito variavelCredito) {
        try {
            elasticsearchClient.index(i -> i.index(nuCpfCnpj).id(idVariavel).document(variavelCredito));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VariavelCredito consultarVariavel(String nuCpfCnpj, String variavelId) {
        try {
            return elasticsearchClient.get(g -> g.index(nuCpfCnpj).id(variavelId), VariavelCredito.class).source();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VariavelCredito> consultarTodasVariavel(String nuCpfCnpj) {
        List<VariavelCredito> variaveis = new ArrayList<>();
        try {
            SearchResponse<VariavelCredito> response = elasticsearchClient.search(s -> s.index(nuCpfCnpj), VariavelCredito.class);

            List<Hit<VariavelCredito>> hits = response.hits().hits();
            for (Hit<VariavelCredito> hit: hits) {
                VariavelCredito variavelCredito = hit.source();
                variaveis.add(variavelCredito);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return variaveis;
    }

    public DeleteResponse deletarVariavel(String nuCpfCnpj, String variavelId) {
        try {
           return elasticsearchClient.delete(d -> d.index(nuCpfCnpj).id(variavelId));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
