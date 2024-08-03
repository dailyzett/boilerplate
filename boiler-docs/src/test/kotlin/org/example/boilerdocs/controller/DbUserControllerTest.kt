package org.example.boilerdocs.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.example.boilerdocs.dto.UserReq
import org.example.boilerdocs.entity.TUser
import org.example.boilerdocs.repo.UserRepository
import org.example.boilerdocs.util.restdocs.NUMBER
import org.example.boilerdocs.util.restdocs.STRING
import org.example.boilerdocs.util.restdocs.dsl.ApiDocumentBuilder
import org.example.boilerdocs.util.restdocs.dsl.RequestBuilder
import org.example.boilerdocs.util.restdocs.dsl.documentBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.templates.TemplateFormats
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDate

@ExtendWith(RestDocumentationExtension::class)
@AutoConfigureRestDocs
@WebMvcTest(DbUserController::class)
class DbUserControllerTest {

    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var userRepository: UserRepository

    private val mapper = ObjectMapper()

    @BeforeEach
    fun setUp(webApplicationContext: WebApplicationContext, restDocumentation: RestDocumentationContextProvider) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder>(
                documentationConfiguration(restDocumentation)
                    .snippets().withTemplateFormat(TemplateFormats.asciidoctor())
                    .and().snippets().withEncoding("UTF-8")
                    .and().operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .build()
    }

    @Test
    fun userDetails() {
        every { userRepository.findTUserByNameAndHobby(any(), any()) } returns TUser(
            id = 1,
            name = "John",
            phone = "123-456-7890",
            hobby = "Hiking",
            email = "james.smith@example.com",
            birthDate = LocalDate.of(1990, 1, 1),
            address = "350 Fifth Ave, New York, NY"
        )

        makeDocument(
            requestBuilderBlock = {
                endPoint = "/db/users"
                method = HttpMethod.POST
                body = UserReq()
            },
            documentBuilderBlock = {
                name { "dbUser" }
                requestBody {
                    "name" type STRING means "이름"
                    "hobby" type STRING means "취미"
                }
                responseBody {
                    "id" type NUMBER means "ID"
                    "name" type STRING means "이름"
                    "phone" type STRING means "폰"
                    "hobby" type STRING means "취미"
                    "email" type STRING means "이메일"
                    "birthDate" type STRING means "생일 선물"
                    "address" type STRING means "주소"
                }
            }
        )
    }

    private fun makeDocument(
        requestBuilderBlock: RequestBuilder.() -> Unit,
        documentBuilderBlock: ApiDocumentBuilder.() -> Unit
    ): ResultActions {
        val requestBuilder = RequestBuilder().apply(requestBuilderBlock).build()
        val documentAction = documentBuilder(documentBuilderBlock)

        return mockMvc.perform(requestBuilder)
            .andExpect(status().isOk)
            .andDo(documentAction)
    }
}