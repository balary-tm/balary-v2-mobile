package tm.com.balary.features.basket.presentation.state

data class OrderFormState(
    val fullName: String = "",
    val fullNameError: Boolean = false,
    val phoneNumber: String = "",
    val phoneError: Boolean = false,
    val district: String = "",
    val street: String = "",
    val house: String = "",
    val room: String = "",
    val floor: String = "",
    val note: String = ""
)
