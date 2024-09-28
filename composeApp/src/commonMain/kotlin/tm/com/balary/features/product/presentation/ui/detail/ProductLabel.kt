package tm.com.balary.features.product.presentation.ui.detail

import androidx.compose.runtime.Composable
import cafe.adriel.lyricist.LocalStrings
import tm.com.balary.features.product.data.entity.single.SingleProductResponse
import tm.com.balary.features.product.data.entity.single.Title

@Composable
fun SingleProductResponse.prettyLabel(): String {
    val strings = LocalStrings.current
    return when(label) {
        "bestseller" -> strings.bestSeller
        "new" -> strings.newProduct
        "recommended" -> strings.recommended
        else -> ""
    }
}