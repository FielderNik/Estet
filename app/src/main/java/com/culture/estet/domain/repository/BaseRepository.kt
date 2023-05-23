package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure

abstract class BaseRepository {
    fun <T> handleRequest(request:() -> T): Either<Failure, T> {
        return try {
            val result = request()
            Either.Right(result)
        } catch (ex: Exception) {
            Either.Left(Failure.Repository(ex))
        }
    }
}