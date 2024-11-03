package org.example.webflux.component

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Validator
import org.springframework.web.server.ResponseStatusException

@Component("bookValidatorV2")
class BookValidator(
    private val validator: Validator,
) {

    val log: Logger = LoggerFactory.getLogger(BookValidator::class.java)

    fun validate(body: Any) {
        val errors = BeanPropertyBindingResult(body, body.javaClass.name)

        this.validator.validate(body, errors)

        if (errors.allErrors.isNotEmpty()) {
            onValidationErrors(errors)
        }
    }

    fun onValidationErrors(errors: BeanPropertyBindingResult) {
        log.error(errors.allErrors.toString())
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, errors.allErrors.toString())
    }
}