package com.fvm.authenticationservice.componentTest.components

import com.fvm.authenticationservice.application.AuthenticationServiceApplication
import io.javalin.Javalin
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
object AuthenticationServiceComponentTest {
    private lateinit var app: Javalin
    private val BASE_URL = "http://localhost:${AuthenticationServiceApplication.SERVER_PORT}/"
    private val request: RequestSpecification = RestAssured.given().baseUri(BASE_URL)

    @BeforeAll
    fun beforeAll() {
        app = AuthenticationServiceApplication.init()
    }

    @AfterAll
    fun afterAll() {
        app.stop()
    }

    private fun getPayload(password: String): String = "{\"password\":\"${password}\"}"

    @Test
    fun `given a valid password then a status OK_200 should be returned`() {
        request
            .`when`()
            .body(
                getPayload("AbTp9!foo")
            )
            .accept(ContentType.JSON)
            .post("/authentication")
            .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.OK_200)
    }

    @Test
    fun `given an invalid password then a status UNPROCESSABLE_ENTITY_422 should be returned`() {
        request
            .`when`()
            .body(
                getPayload("AbTp")
            )
            .accept(ContentType.JSON)
            .post("/authentication")
            .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.UNPROCESSABLE_ENTITY_422)
    }

    @Test
    fun `given an invalid payload is submitted then a status BAD_REQUEST_400 should be returned`() {
        request
            .`when`()
            .body( "abcd")
            .accept(ContentType.JSON)
            .post("/authentication")
            .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.BAD_REQUEST_400)
    }
}
