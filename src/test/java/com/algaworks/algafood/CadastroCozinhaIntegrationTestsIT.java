package com.algaworks.algafood;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIntegrationTestsIT {

    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        flyway.migrate();
    }

    @Test
    public void testReturnStatus200WhenSearchByCozinha() {
        RestAssured.given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testNeedsToHave4kitchensWhenSearchAllKitchens() {
        RestAssured.given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", Matchers.hasSize(4))
            .body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
    }

    @Test
    public void testNeedsToReturn201_WhenKitchenCreated() {
        RestAssured.given()
            .body("{ \"nome\": \"Chinesa\"}")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }


    // TESTES DE INTEGRAÇAO

    // @Autowired
    // private CadastroCozinhaService cadastroCozinhaService;

    // @Test
    // public void withSuccess() {
    //     // cenário
    //     Cozinha cozinha = new Cozinha();
    //     cozinha.setNome("Chinesa");

    //     // acão
    //     cozinha = cadastroCozinhaService.salvar(cozinha);

    //     //validação
    //     assertTrue(cozinha != null);
    //     assertTrue(cozinha.getId() != null);
    // }

    // @Test(expected = DataIntegrityViolationException.class)
    // public void failWithEmptyName() {
    //     Cozinha cozinha = new Cozinha();
    //     cozinha.setNome(null);

    //     cadastroCozinhaService.salvar(cozinha);
    // }

    // @Test(expected = EntidadeEmUsoException.class)
    // public void failWhenRemoveKitchenInUse() {
    //     cadastroCozinhaService.excluir(1L);
    // }

    // @Test(expected = CozinhaNaoEncontradaException.class)
    // public void failWhenRemoveNonExistentKitchen() {
    //     cadastroCozinhaService.excluir(20L);
    // }
}
