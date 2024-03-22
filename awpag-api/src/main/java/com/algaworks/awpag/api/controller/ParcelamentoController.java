package com.algaworks.awpag.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.awpag.api.mapper.ParcelamentoMapper;
import com.algaworks.awpag.api.model.ParcelamentoModel;
import com.algaworks.awpag.api.model.input.ParcelamentoInput;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import com.algaworks.awpag.service.ParcelamentoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

	private final ParcelamentoRepository parcelamentoRepository;
	private final ParcelamentoService parcelamentoService;
	private final ParcelamentoMapper parcelamentoMapper;
	
	@GetMapping
	public List<ParcelamentoModel> listar() {
		return parcelamentoMapper.toCollectionModel(parcelamentoRepository.findAll());
	}

	@GetMapping("/{parcelamentoId}")
	public ResponseEntity<ParcelamentoModel> buscarPorId(@PathVariable Long parcelamentoId) {
		return parcelamentoRepository.findById(parcelamentoId)
				.map(parcelamentoMapper::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ParcelamentoModel cadastrar(@Valid @RequestBody ParcelamentoInput parcelamentoInput) {
		Parcelamento novoParcelamento = parcelamentoMapper.toEntity(parcelamentoInput);
		Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(novoParcelamento);
		return parcelamentoMapper.toModel(parcelamentoCadastrado) ;
	}
}
