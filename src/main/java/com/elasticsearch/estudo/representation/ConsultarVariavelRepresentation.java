package com.elasticsearch.estudo.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarVariavelRepresentation {

    private String nuCpfCnpj;
    private String id;
}
