package no.jlwcrews.models

import java.time.LocalDateTime

data class User(
    val username: String,
    val password: String?,
    val created: LocalDateTime,
    val email: String
)