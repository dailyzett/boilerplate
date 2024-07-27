package org.example.boilerdocs.controller

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.example.boilerdocs.util.restdocs.*
import org.example.boilerdocs.util.restdocs.customResponseFields
import org.example.boilerdocs.util.restdocs.type
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration

@SpringBootTest
@ExtendWith(RestDocumentationExtension::class)
class UserControllerTest {

    val baseUrl = "https://jsonplaceholder.typicode.com"

    lateinit var spec: RequestSpecification

    @BeforeEach
    fun setUp(restDocumentation: RestDocumentationContextProvider) {
        spec = RequestSpecBuilder()
            .addFilter(documentationConfiguration(restDocumentation))
            .build()
    }

    @Test
    fun `UserController - userList returns OK and list of users`() {
        RestAssured
            .given(this.spec)
            .filter(
                document(
                    "userList", customResponseFields(
                        "[].id" type NUMBER means "고유 아이디",
                        "[].name" type STRING means "이름",
                        "[].username" type STRING means "가입 이름",
                        "[].email" type STRING means "이메일",
                        "[].phone" type STRING means "전화 번호",
                        "[].website" type STRING means "웹사이트",
                        *CommonSnippet.addressArray().toTypedArray(),
                        *CommonSnippet.companyArray().toTypedArray()
                    )
                )
            )
            .contentType(ContentType.JSON)
            .`when`()
            .get("$baseUrl/users")
            .then()
            .log().all()
            .statusCode(200)
    }

    @Test
    fun `UserController - user returns OK and detail of user`() {
        RestAssured
            .given(this.spec)
            .filter(
                document(
                    "user", customResponseFields(
                        "id" type NUMBER means "고유 아이디",
                        "name" type STRING means "이름",
                        "username" type STRING means "가입 이름",
                        "email" type STRING means "이메일",
                        "phone" type STRING means "전화 번호",
                        "website" type STRING means "웹사이트",
                        *CommonSnippet.address().toTypedArray(),
                        *CommonSnippet.company().toTypedArray(),
                    )
                )
            )
            .contentType(ContentType.JSON)
            .`when`()
            .get("$baseUrl/users/1")
            .then()
            .log().all()
            .statusCode(200)
    }
}