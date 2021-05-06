package com.example.batch2week5.responses

data class ListResponse<T>(
    var msg : String,
    var status : Int,
    var data : List<T>
)

data class SingleResponse<T>(
    var msg : String,
    var status : Int,
    var data : T
)