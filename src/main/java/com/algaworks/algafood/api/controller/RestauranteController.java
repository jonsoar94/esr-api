package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteDTO;
import com.algaworks.algafood.api.model.input.restaurante.RestauranteInputDTO;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteDTO> listar() {
        List<Restaurante> restaurantes = cadastroRestauranteService.listar();
        return restauranteModelAssembler.toCollectionDTO(restaurantes);
    }

    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscar(restauranteId);
        return restauranteModelAssembler.toDTO(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
        Restaurante restaurante = restauranteInputDisassembler.toDomain(restauranteInputDTO);

        return restauranteModelAssembler.toDTO(cadastroRestauranteService.adicionar(restaurante));
    }

    @PutMapping("/{restauranteId}")
    public RestauranteDTO atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
        //convert to domain
        Restaurante restaurante = restauranteInputDisassembler.toDomain(restauranteInputDTO);

        //conver to DTO and return
        return restauranteModelAssembler.toDTO(cadastroRestauranteService.atualizar(restauranteId, restaurante));
    }
    
    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
    	this.cadastroRestauranteService.ativar(restauranteId);
    }
    
    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
    	this.cadastroRestauranteService.inativar(restauranteId);
    }

    // @PatchMapping("/{restauranteId}")
    // public RestauranteDTO atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest httpServletRequest) {
    //     Restaurante restaurante = cadastroRestauranteService.buscar(restauranteId);

    //     merge(campos, restaurante, httpServletRequest);
    //     validate(restaurante, "restaurante");

    //     return atualizar(restauranteId, restaurante);
    // }

    // private void validate(Restaurante restaurante, String objectName) {
    //     BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
    //     this.validator.validate(restaurante, bindingResult);

    //     if(bindingResult.hasErrors()) {
    //         throw new ValidacaoException(bindingResult);
    //     }
    // }

    // private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino,
    //         HttpServletRequest httpServletRequest) {
    //     ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(httpServletRequest);

    //     try {
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
    //         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    //         Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

    //         dadosOrigem.forEach((propriedadeChave, propriedadeValor) -> {
    //             Field field = ReflectionUtils.findField(Restaurante.class, propriedadeChave);
    //             field.setAccessible(true);

    //             Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

    //             ReflectionUtils.setField(field, restauranteDestino, novoValor);
    //         });
    //     } catch (IllegalArgumentException e) {
    //         Throwable rootCause = ExceptionUtils.getRootCause(e);
    //         throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
    //     }
    // }
}
