package tm.com.balary.features.address.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.archive
import balary.composeapp.generated.resources.location_bee
import balary.composeapp.generated.resources.location_filled
import balary.composeapp.generated.resources.save_fill
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.common.BalaryMaps
import tm.com.balary.common.LatLng
import tm.com.balary.features.basket.presentation.ui.order.IconTitle

@Composable
fun AddressFields(navHostController: NavHostController) {
    val fullName = rememberSaveable {
        mutableStateOf("")
    }
    val strings = LocalStrings.current
    Column(
        Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.surface
        ).verticalScroll(rememberScrollState())
    ) {
        Box(
            Modifier.fillMaxWidth()
        ) {

            BalaryMaps(
                modifier = Modifier.fillMaxWidth().height(250.dp),
                initialPoint = LatLng(0.0, 0.0),
                onMapClick = {
                }
            )

            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.offset(
                    x = 16.dp,
                    y = 16.dp
                ).size(24.dp).clip(CircleShape).clickable {
                    navHostController.navigateUp()
                }
            )
        }

        Spacer(
            Modifier.height(8.dp)
        )

        Column(
            Modifier.fillMaxWidth().background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(20.dp)
            ).padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(Res.drawable.location_bee),
                    contentDescription = "location",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.width(40.dp).height(48.dp)
                )

                Button(
                    shape = RoundedCornerShape(4.dp),
                    onClick = {

                    }
                ) {
                    Text(
                        strings.edit,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }

            Text(
                strings.courierDescription,
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
                value = fullName.value,
                onValueChange = {
                    fullName.value = it
                },
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
                value = fullName.value,
                onValueChange = {
                    fullName.value = it
                },
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
                value = fullName.value,
                onValueChange = {
                    fullName.value = it
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
                value = fullName.value,
                onValueChange = {
                    fullName.value = it
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
                value = fullName.value,
                onValueChange = {
                    fullName.value = it
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
                value = fullName.value,
                onValueChange = {
                    fullName.value = it
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
                value = fullName.value,
                onValueChange = {
                    fullName.value = it
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
                }
            ) {
                Text(
                    strings.accept, style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    ), color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Spacer(
            Modifier.height(8.dp)
        )
    }
}