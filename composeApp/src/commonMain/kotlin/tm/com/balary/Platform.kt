package tm.com.balary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform