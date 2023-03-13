package com.example.privyproject.extension

import androidx.lifecycle.MutableLiveData
import retrofit2.Response

data class DataHolder<T>(
    var responseType: Status,
    var data: T? = null,
    var error: Error? = null
)

sealed class Status {
    object SUCCESSFUL : Status()
    object ERROR : Status()
    object LOADING : Status()
}

fun <T : Any?> MutableLiveData<DataHolder<T?>>.loadingData() = apply {
    postValue(DataHolder(responseType = Status.LOADING))
}


fun <T : Any?> MutableLiveData<DataHolder<T?>>.responseData(responseMethod: Response<T>) = apply {
    responseMethod.apply {
        if (isSuccessful) {
            postValue(
                DataHolder(responseType = Status.SUCCESSFUL, data = this.body())
            )
        } else {
            postValue(
                DataHolder(
                    responseType = Status.ERROR,
                    error = Error("invalid response")
                )
            )
        }
    }
}