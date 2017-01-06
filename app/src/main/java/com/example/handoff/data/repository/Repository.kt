package com.example.handoff.data.repository

interface Repository<T> {

    fun add(item: T)

    fun add(items: Iterable<T>)

    fun update(item: T)

    fun remove(item: T)

    fun remove(spec: Specification)

    fun query(spec: Specification): List<T>
}