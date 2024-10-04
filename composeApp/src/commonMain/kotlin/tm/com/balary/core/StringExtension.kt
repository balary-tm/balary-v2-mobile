package tm.com.balary.core

fun String.prettyMarkdown(): String {
    return """
        $this
    """.trimIndent()
}