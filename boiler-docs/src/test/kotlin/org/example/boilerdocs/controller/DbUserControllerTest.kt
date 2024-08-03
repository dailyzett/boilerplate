package org.example.boilerdocs.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.example.boilerdocs.dto.UserReq
import org.example.boilerdocs.entity.TUser
import org.example.boilerdocs.repo.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.templates.TemplateFormats
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
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

        val requestBuilder = post("/db/users")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(UserReq()))

        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andDo(
                document(
                    "user",
                    requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("hobby").type(JsonFieldType.STRING).description("취미")
                    ),
                    responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("ID"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("phone").type(JsonFieldType.STRING).description("폰"),
                        fieldWithPath("hobby").type(JsonFieldType.STRING).description("취미"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                        fieldWithPath("birthDate").type(JsonFieldType.STRING).description("생일 선물"),
                        fieldWithPath("address").type(JsonFieldType.STRING).description("주소"),
                    )
                )
            )
    }
}