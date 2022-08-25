package com.example.fampayassignmentapp.commons

import com.example.fampayassignmentapp.data.remote.response.CardGroupResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import retrofit2.Response

fun <T> returnAuthOrServerErrorListResponse(
    response: Response<CardGroupResponse>,
): Resource.Error<T> {
    val json = response.errorBody()?.string()
    val error = Gson().fromJson(json, ErrorResponse::class.java)
    return if (response.code() == 401) {
        Resource.Error(
            Pair(
                Failure.UnauthorizedError, error
            )
        )
    } else if (response.code() == 404) {
        Resource.Error(
            Pair(
                Failure.UnauthorizedError,
                ErrorResponse("Url Not found")
            )
        )
    } else {
        Resource.Error(
            Pair(
                Failure.ServerError,
                ErrorResponse("Something went wrong")
            )
        )
    }
}

fun <T> returnExceptionResponse(
    exception: Exception
): Resource.Error<T> {
    return if (exception is JSONException || exception is JsonSyntaxException) {
        Resource.Error(
            Pair(
                Failure.ParsingError,
                ErrorResponse(exception.message, exception)
            )
        )
    } else {
        Resource.Error(
            Pair(
                Failure.NetworkConnection,
                ErrorResponse(exception.message, exception)
            )
        )
    }
}
