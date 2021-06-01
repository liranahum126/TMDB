package com.example.tmdb.data.interfaces

interface Mappable<T> {
    fun toResult(): T
}