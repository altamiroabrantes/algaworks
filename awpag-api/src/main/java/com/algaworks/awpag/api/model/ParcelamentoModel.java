package com.algaworks.awpag.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.awpag.domain.model.Parcelamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelamentoModel {
	
	private Long id;
	private ClienteResumoModel cliente;
	private String descricao;
	private BigDecimal valorTotal;
	private Integer parcelas;
	private OffsetDateTime dataCriacao;
	
}
