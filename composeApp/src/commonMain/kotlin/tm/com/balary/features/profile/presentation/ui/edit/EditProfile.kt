package tm.com.balary.features.profile.presentation.ui.edit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.edit
import balary.composeapp.generated.resources.eye
import balary.composeapp.generated.resources.female
import balary.composeapp.generated.resources.male
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.auth.presentation.ui.BackScreen
import tm.com.balary.features.profile.presentation.ui.profile.ProfileAvatar

class EditProfileScreen : Screen {
    @Composable
    override fun Content() {
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val show = rememberSaveable {
        mutableStateOf(false)
    }

    val open = remember {
        mutableStateOf(false)
    }

    val strings = LocalStrings.current

    val dateState = rememberDatePickerState()

    if(open.value) {
        DatePickerDialog(
            onDismissRequest = {
                open.value = false
            },
            confirmButton = {
                Button(
                    onClick = {

                    }
                ) {
                    Text("OK")
                }
            },
        ) {
            DatePicker(
                dateState
            )
        }
    }
    BackScreen(modifier, title = strings.profileInfo, navHostController = navHostController) {
        Column(
            Modifier.fillMaxWidth().background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            ).verticalScroll(rememberScrollState()).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileAvatar()
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Balary",
                onValueChange = {
                },
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.edit),
                        contentDescription = "edit",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(24.dp)
                    )
                },
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
                value = "+993 62 623698",
                onValueChange = {
                },
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.edit),
                        contentDescription = "edit",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(24.dp)
                    )
                },
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
                    Text(strings.phoneNumber)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Balary@mail.com",
                onValueChange = {
                },
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.edit),
                        contentDescription = "edit",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(24.dp)
                    )
                },
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
                    Text(strings.email2)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().clickable {
                    open.value = true
                },
                value = "dd-mm-yyyy",
                onValueChange = {
                },
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.edit),
                        contentDescription = "edit",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(24.dp)
                    )
                },
                readOnly = true,
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
                    Text(strings.dateOfBirth)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "123456",
                onValueChange = {
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
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.eye),
                        contentDescription = "password",
                        tint = if (show.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(24.dp).clip(CircleShape).clickable {
                            show.value = show.value.not()
                        }
                    )
                },
                visualTransformation = if (show.value) VisualTransformation.None else PasswordVisualTransformation(),
                label = {
                    Text(strings.password)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (show.value) KeyboardType.Text else KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )

            SingleChoiceSegmentedButtonRow(Modifier.fillMaxWidth()) {
                SegmentedButton(
                    modifier = Modifier.weight(1f),
                    selected = true,
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.4f
                        ),
                        inactiveContainerColor = MaterialTheme.colorScheme.surfaceDim
                    ),
                    icon = {},
                    shape = RoundedCornerShape(
                        topStart = 4.dp,
                        bottomStart = 4.dp
                    ),
                    onClick = {

                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            painter = painterResource(Res.drawable.male),
                            contentDescription = "male",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Text(strings.male, style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ), color = MaterialTheme.colorScheme.onSurface)
                    }
                }

                SegmentedButton(
                    modifier = Modifier.weight(1f),
                    selected = false,
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.4f
                        ),
                        inactiveContainerColor = MaterialTheme.colorScheme.surfaceDim
                    ),
                    icon = {},
                    shape = RoundedCornerShape(
                        topStart = 4.dp,
                        bottomStart = 4.dp
                    ),
                    onClick = {

                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            painter = painterResource(Res.drawable.female),
                            contentDescription = "female",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Text(strings.female, style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ), color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {

                }
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "check",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(strings.save, style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W700
                ), color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}