package com.example.tmdb.data

interface Mappable<T> {
    fun toResult(): T
}