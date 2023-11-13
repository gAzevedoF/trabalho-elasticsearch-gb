package com.elasticsearch.estudo.request;

import com.elasticsearch.estudo.domain.VariavelCredito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariavelCreditoRepresentation {
    private String nuCpfCnpj;
    private String id;
    private VariavelCredito variavelCredito;
}
