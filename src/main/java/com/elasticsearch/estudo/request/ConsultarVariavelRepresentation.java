package com.elasticsearch.estudo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarVariavelRepresentation {
    private String numeroCpfCnpj;
    private String id;
}
