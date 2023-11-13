package com.elasticsearch.estudo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaIndicesResponse {
    private Set<String> indices;
}
