package tm.com.balary.features.basket.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import tm.com.balary.features.basket.presentation.state.OrderFormState

class OrderFormViewModel : ViewModel() {
    val orderFormState = mutableStateOf(OrderFormState())

    fun onFullNameChange(name: String) {
        orderFormState.value = orderFormState.value.copy(
            fullName = name,
            fullNameError = name.length<2
        )
    }

    fun onPhoneChange(value: String) {
        orderFormState.value = orderFormState.value.copy(
            phoneNumber = value,
            phoneError = value.length<7
        )
    }

    fun onDistrictChange(value: String) {
        orderFormState.value = orderFormState.value.copy(
            district = value
        )
    }

    fun onStreetChange(value: String) {
        orderFormState.value = orderFormState.value.copy(
            street = value
        )
    }

    fun onHouseChange(value: String) {
        orderFormState.value = orderFormState.value.copy(
            house = value
        )
    }

    fun onRoomChange(value: String) {
        orderFormState.value = orderFormState.value.copy(
            room = value
        )
    }

    fun onFloorChange(value: String) {
        orderFormState.value = orderFormState.value.copy(
            floor = value
        )
    }

    fun onNoteChange(value: String) {
        orderFormState.value = orderFormState.value.copy(
            note = value
        )
    }

    fun validateForm(): Boolean {
        val it = orderFormState.value
        orderFormState.value = orderFormState.value.copy(
            phoneError = it.phoneNumber.length<7,
            fullNameError = it.fullName.length<2
        )
        return orderFormState.value.phoneError.not() && orderFormState.value.fullNameError.not()
    }
}