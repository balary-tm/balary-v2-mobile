package tm.com.balary.features.basket.presentation.ui.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.archive
import balary.composeapp.generated.resources.calendar
import balary.composeapp.generated.resources.delivery_truck
import balary.composeapp.generated.resources.info_fill
import balary.composeapp.generated.resources.location_filled
import balary.composeapp.generated.resources.save_fill
import balary.composeapp.generated.resources.wallet
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.address.presentation.ui.SelectAddressDialog
import tm.com.balary.features.basket.presentation.ui.PriceInfo
import tm.com.balary.features.basket.presentation.viewmodel.BasketViewModel
import tm.com.balary.features.basket.presentation.viewmodel.OrderDayType
import tm.com.balary.features.basket.presentation.viewmodel.OrderFormViewModel
import tm.com.balary.features.map.presentation.ui.MapDialog
import tm.com.balary.state.LocalAuth
import tm.com.balary.state.LocalDarkMode
import tm.com.balary.ui.AlsFontFamily
import tm.com.balary.ui.AppError
import tm.com.balary.ui.AppLoading
import kotlin.math.ceil

class OrderScreen : Screen {
    @Composable
    override fun Content() {
    }
}


@OptIn(KoinExperimentalAPI::class)
@Composable
fun Order(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val basketViewModel: BasketViewModel = koinNavViewModel()
    val extraState = basketViewModel.orderExtraState.collectAsState()
    val basketState = basketViewModel.basketState.collectAsState()

    LaunchedEffect(true) {
        basketViewModel.getBasket()
        basketViewModel.initOrderExtra()
    }

    val navigator = LocalNavigator.currentOrThrow
    val fullName = rememberSaveable {
        mutableStateOf("")
    }
    val openAddress = remember {
        mutableStateOf(false)
    }
    val openMap = remember {
        mutableStateOf(false)
    }
    val openSuccess = remember {
        mutableStateOf(false)
    }

    val formViewModel: OrderFormViewModel = koinNavViewModel()

    val formState = formViewModel.orderFormState
    val strings = LocalStrings.current

    val isDark = LocalDarkMode.current
    val toaster = rememberToasterState()
    Toaster(
        state = toaster,
        darkTheme = isDark.value,
        richColors = true,
        alignment = Alignment.TopCenter
    )

    OrderSuccess(
        open = openSuccess.value,
        onClose = {
            openSuccess.value = false
        }
    )
    MapDialog(
        open = openMap.value,
        onClose = {
            openMap.value = false
        }
    )
    SelectAddressDialog(open = openAddress.value) {
        openAddress.value = false
    }
    Column(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            Modifier.fillMaxWidth().background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            ).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(24.dp).clip(CircleShape).clickable {
                    navigator.pop()
                }
            )

            Text(
                strings.makeOrder,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        val paymentType = basketViewModel.selectedPaymentType



        if(extraState.value.loading) {
            AppLoading(Modifier.fillMaxSize())
        } else if(extraState.value.error.isNullOrEmpty().not()) {
            AppError(Modifier.fillMaxSize())
        } else {
            extraState.value.extra?.let { extra->
                val deliveryPrice = basketViewModel.deliveryPrice
                Column(Modifier.fillMaxSize().imePadding().verticalScroll(rememberScrollState())) {
                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Column(
                        Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconTitle(
                            icon = painterResource(Res.drawable.wallet),
                            title = strings.paymentType
                        )
                        CheckTextDesc(
                            Modifier.fillMaxWidth().clickable {
                                basketViewModel.setSelectedPaymentType("cash")
                            },
                            text = strings.cash,
                            subTitle = strings.cashDescription,
                            checked = paymentType.value == "cash"
                        )

                        CheckTextDesc(
                            Modifier.fillMaxWidth().clickable {
                                basketViewModel.setSelectedPaymentType("card")
                            },
                            text = strings.bank,
                            subTitle = strings.bankDescription,
                            checked = paymentType.value == "card"
                        )
                    }
                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Column(
                        Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconTitle(
                                modifier = Modifier.weight(1f),
                                icon = painterResource(Res.drawable.location_filled),
                                title = strings.yourAddress
                            )

                            IconButton(
                                onClick = {
                                    openAddress.value = true
                                }
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.archive),
                                    contentDescription = "archive",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.save_fill),
                                    contentDescription = "save",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                        }

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = formState.value.fullName,
                            onValueChange = {
                                formViewModel.onFullNameChange(it)
                            },
                            isError = formState.value.fullNameError,
                            maxLines = 1,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                errorTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            label = {
                                Text(strings.fullName)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = formState.value.phoneNumber,
                            onValueChange = {
                                formViewModel.onPhoneChange(it)
                            },
                            isError = formState.value.phoneError,
                            prefix = {
                                Text(
                                    "+993", style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.W700
                                    )
                                )
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                errorTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            label = {
                                Text(strings.phoneNumber)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone,
                                imeAction = ImeAction.Next
                            )
                        )

                        Text(
                            strings.districtAndStreet,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = formState.value.district,
                            onValueChange = {
                                formViewModel.onDistrictChange(it)
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                errorTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            label = {
                                Text(strings.district)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = formState.value.street,
                            onValueChange = {
                                formViewModel.onStreetChange(it)
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                errorTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            label = {
                                Text(strings.street)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = formState.value.house,
                            onValueChange = {
                                formViewModel.onHouseChange(it)
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                errorTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            label = {
                                Text(strings.buildingNumber)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = formState.value.room,
                            onValueChange = {
                                formViewModel.onRoomChange(it)
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                errorTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            label = {
                                Text(strings.apartmentNumber)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = formState.value.floor,
                            onValueChange = {
                                formViewModel.onFloorChange(it)
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                errorTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            label = {
                                Text(strings.floorNumber)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                openMap.value = true
                            }
                        ) {
                            Text(
                                strings.showOnMap, style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.W700
                                ), color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Column(
                        Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val deliveryType = basketViewModel.selectedDeliveryType
                        IconTitle(
                            icon = painterResource(Res.drawable.delivery_truck),
                            title = strings.deliveryType
                        )
                        TimeCheck(
                            modifier = Modifier.fillMaxWidth().clickable {
                                basketViewModel.setDeliveryPrice(extra.delivery_price?:0.0)
                                basketViewModel.setSelectedDeliveryType("standard")
                            },
                            text = strings.standardDelivery,
                            checked = deliveryType.value == "standard"
                        )
                        TimeCheck(
                            modifier = Modifier.fillMaxWidth().clickable {
                                basketViewModel.setDeliveryPrice(extra.express_order_price?:0.0)
                                basketViewModel.setSelectedDeliveryType("express")
                            },
                            text = strings.expressDelivery.replace("{price}", extra.express_order_price.toString()),
                            checked = deliveryType.value == "express",
                            subTitle = strings.expressDeliveryDesc
                        )
                        TimeCheck(
                            modifier = Modifier.fillMaxWidth().clickable {
                                basketViewModel.setDeliveryPrice(0.0)
                                basketViewModel.setSelectedDeliveryType("self")
                            },
                            text = strings.manualDelivery,
                            checked = deliveryType.value == "self",
                            subTitle = "Туркменистан, г. Ашхабад. , ул. 2028 (Ата Говшудова), \n" +
                                    "дом 47 «A» 1-ый этаж"
                        )
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.info_fill),
                                contentDescription = "info",
                                tint = Color(0xFF614FE0)
                            )
                            val text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontFamily = AlsFontFamily(),
                                        fontWeight = FontWeight.W700,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                ) {
                                    append("Iş wagty ")
                                }
                                withStyle(
                                    SpanStyle(
                                        fontFamily = AlsFontFamily(),
                                        fontWeight = FontWeight.W700,
                                        fontSize = 12.sp,
                                        color = Color(0xFF614FE0)
                                    )
                                ) {
                                    append("09:00")
                                }
                                withStyle(
                                    SpanStyle(
                                        fontFamily = AlsFontFamily(),
                                        fontWeight = FontWeight.W700,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                ) {
                                    append(" -dan ")
                                }
                                withStyle(
                                    SpanStyle(
                                        fontFamily = AlsFontFamily(),
                                        fontWeight = FontWeight.W700,
                                        fontSize = 12.sp,
                                        color = Color(0xFF614FE0)
                                    )
                                ) {
                                    append("21:00")
                                }
                                withStyle(
                                    SpanStyle(
                                        fontFamily = AlsFontFamily(),
                                        fontWeight = FontWeight.W700,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                ) {
                                    append(" -a çenli ")
                                }
                            }
                            Text(
                                text
                            )


                        }

                        if(deliveryType.value == "self") {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                onClick = {

                                }
                            ) {
                                Text(
                                    strings.showOurAddressFromMap, style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.W700
                                    ), color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }

                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Column(
                        Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val selectedDay = basketViewModel.selectedDay
                        val selectedTimes = basketViewModel.selectedTimes
                        val intervalId = basketViewModel.selectedInterval
                        IconTitle(
                            icon = painterResource(Res.drawable.calendar),
                            title = strings.deliveryTime
                        )

                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            extra.intervals?.today?.let { today->
                                if(today.isNotEmpty()) {
                                    OrderTime(Modifier.weight(1f).clickable {
                                        basketViewModel.setSelectedDay(OrderDayType.TODAY)
                                        basketViewModel.setSelectedTimes(today)
                                    }, title = strings.today, selected = selectedDay.value == OrderDayType.TODAY)
                                }
                            }
                            extra.intervals?.tomorrow?.let { tomorrow->
                                if(tomorrow.isNotEmpty()) {
                                    OrderTime(Modifier.weight(1f).clickable {
                                        basketViewModel.setSelectedDay(OrderDayType.TOMORROW)
                                        basketViewModel.setSelectedTimes(tomorrow)
                                    }, title = strings.tomorrow, selected = selectedDay.value == OrderDayType.TOMORROW)
                                }
                            }
                        }
                        repeat(ceil(selectedTimes.value.count()/2f).toInt()) { index->
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                val firstIndex = index.times(2)
                                OrderTimeCheckText(
                                    modifier = Modifier.weight(1f).clickable {
                                        basketViewModel.setSelectedInterval(selectedTimes.value[firstIndex].id)
                                    },
                                    text = "${selectedTimes.value[firstIndex].start_time}-${selectedTimes.value[firstIndex].end_time}",
                                    checked = intervalId.value == selectedTimes.value[firstIndex].id
                                )
                                val secondIndex = index.times(2).plus(1)
                                if(secondIndex<selectedTimes.value.count()) {
                                    OrderTimeCheckText(
                                        modifier = Modifier.weight(1f).clickable {
                                            basketViewModel.setSelectedInterval(selectedTimes.value[secondIndex].id)
                                        },
                                        text = "${selectedTimes.value[secondIndex].start_time}-${selectedTimes.value[secondIndex].end_time}",
                                        checked = intervalId.value == selectedTimes.value[secondIndex].id
                                    )
                                }
                            }

                        }
                        Column(Modifier.fillMaxWidth().padding(vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            PriceInfo(
                                modifier = Modifier.fillMaxWidth(),
                                title = strings.price,
                                value = "${basketState.value.calculation.totalWithoutDiscount} m."
                            )
                            PriceInfo(
                                modifier = Modifier.fillMaxWidth(),
                                title = strings.deliveryPrice,
                                value = deliveryPrice.value.toString().plus(" m.")
                            )
                            PriceInfo(
                                modifier = Modifier.fillMaxWidth(),
                                title = strings.discounts,
                                value = "-${basketState.value.calculation.discount} m.",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Column(
                        Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = formState.value.note,
                            onValueChange = {
                                formViewModel.onNoteChange(it)
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                errorTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            label = {
                                Text(strings.notes)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Column(
                        Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            strings.deliveryHome,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            "«Balary» — Aşgabatdaky onlaýn-supermarket, harytlary öýüňize ýa-da ofisiňize eltip bermek hyzmaty. Biziň internet-magazinimiz azyk harytlaryň we beýleki harytlaryň görnüşlerini uly saýlawyny hödürleýär. Her bir atlandrylan haryt pugta hil barlagyndan geçirilýär.\n" +
                                    "«Balary» harytlary sargamak üçin amatly wagt araçäkleri, şol sanda sargyt edilen güni eltip bermegi bilen ýokary derejeli hyzmaty kepillendirýär.\n" +
                                    "Satyn alynan harytlary internet-magazinimiziň web-sahypasynda, telefon programmasyda ýa-da telefon arkalyy öýüňize eltip bermek hyzmaty bilen sargyt edip bilersiňiz: +993 (12) 92 40 71.\n" +
                                    "Balary – bu diňe bir ter önümler bolman, hemişe gyzykly aksiýalar we haýyrly hödürlenmeler, möwsümleýin saýlanan harytlar bolup durýar!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Column(
                        Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                strings.total,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.W900,
                                    fontSize = 18.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                "${basketState.value.calculation.total.plus(deliveryPrice.value)} m.",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.W900,
                                    fontSize = 18.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        val sendOrderState = basketViewModel.sendOrderState.collectAsState()



                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                val isValid = formViewModel.validateForm()
                                if(isValid) {
                                    basketViewModel.makeOrder(
                                        orderFormState = formState.value,
                                        onError = { message->
                                            message?.let {
                                                toaster.show(
                                                    message,
                                                    type = ToastType.Error
                                                )
                                            }
                                        },
                                        onSuccess = {
                                            openSuccess.value = true
                                        }
                                    )
                                }
                            }
                        ) {
                            if(sendOrderState.value.loading) {
                                CircularProgressIndicator(Modifier.size(30.dp), color = MaterialTheme.colorScheme.onPrimary)
                            } else {
                                Text(
                                    strings.confirmOrder,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.W700
                                    ),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }

                    Spacer(
                        Modifier.height(8.dp)
                    )
                }
            }
        }
    }
}