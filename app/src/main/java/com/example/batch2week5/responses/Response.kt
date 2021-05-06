package com.example.batch2week5.responses

data class ListResponse<T>(
    var message : String,
    var status : Int,
    var data : List<T>
)

data class SingleResponse<T>(
    var message : String,
    var status : Int,
    var data : T
)