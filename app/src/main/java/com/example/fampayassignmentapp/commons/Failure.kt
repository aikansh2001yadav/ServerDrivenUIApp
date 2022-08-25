package com.example.fampayassignmentapp.commons

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object ParsingError : Failure()
    object UnauthorizedError : Failure()
    object UnknownError: Failure()

    abstract class FeatureFailure : Failure()
}
