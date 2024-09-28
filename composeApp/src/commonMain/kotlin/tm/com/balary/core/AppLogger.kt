package tm.com.balary.core

class AppLogger {
    companion object {
        fun log(message: String, tag: String = "AppLogger") {
            println("-------------------API-STARTING----------------------")
            println("$tag : $message")
            println("-------------------API-ENDED-------------------------")
        }
    }
}