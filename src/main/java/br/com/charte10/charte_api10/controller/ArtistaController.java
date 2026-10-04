package br.com.charte10.charte_api10.controller;

import br.com.charte10.charte_api10.dto.request.ArtistaRequestDTO;
import br.com.charte10.charte_api10.dto.response.ArtistaResponseDTO;
import br.com.charte10.charte_api10.service.ArtistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistaResponseDTO cadastrar(
            @RequestBody @Valid ArtistaRequestDTO dto) {

        return service.cadastrar(dto);
    }

    @GetMapping
    public List<ArtistaResponseDTO> listar() {

        return service.listar();
    }

    @GetMapping("/{id}")
    public ArtistaResponseDTO buscar(
            @PathVariable Integer id) {

        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable Integer id) {

        service.excluir(id);
    }
}