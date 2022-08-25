package com.example.fampayassignmentapp.commons

// Adding ErrorResponse
data class ErrorResponse(
    var message: String?,
    var exception: Exception? = null
)
