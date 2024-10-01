package com.balary.store

interface Platform {
    val name: String
}

const val STORE_KEY_TEST = "store_key"

expect fun getPlatform(): Platform