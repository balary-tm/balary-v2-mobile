package tm.com.balary.features.home.domain.model

import tm.com.balary.features.product.domain.model.ProductModel

data class HomeProductModel(
    val discountProducts: List<ProductModel>,
    val newProducts: List<ProductModel>,
    val discountSlides: List<SlideModel>
)
