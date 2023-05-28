package com.culture.estet.core.funcional

sealed class Failure(open val exception: Throwable? = null) {

    data class Database(override val exception: Throwable?): Failure(exception) {

    }

    data class Repository(override val exception: Throwable?): Failure(exception)

    sealed class Feature(override var exception: Throwable? = null): Failure(exception) {

    }

}
