package com.elasticsearch.estudo.response;

import com.elasticsearch.estudo.domain.VariavelCredito;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaVariavelResponse {
    private String resultado;
    private VariavelCredito variavelCredito;
}
