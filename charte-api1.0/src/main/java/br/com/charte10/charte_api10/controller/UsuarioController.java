package br.com.charte10.charte_api10.controller;

import br.com.charte10.charte_api10.dto.UsuarioRequestDTO;
import br.com.charte10.charte_api10.entity.Usuario;
import br.com.charte10.charte_api10.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public Usuario cadastrar(@RequestBody @Valid UsuarioRequestDTO dto){

        return service.cadastrar(dto);

    }

    @GetMapping
    public List<Usuario> listar(){

        return service.listarTodos();

    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Integer id){

        return service.buscarPorId(id);

    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id){

        service.excluir(id);

    }

}