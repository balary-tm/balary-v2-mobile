package tm.com.balary.features.product.presentation.ui.review

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.baseline_camera_alt_24
import balary.composeapp.generated.resources.close_filled
import io.github.vinceglb.filekit.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.core.PickerMode
import io.github.vinceglb.filekit.core.PickerType
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.product.presentation.ui.detail.RatingBar
import tm.com.balary.ui.AppCheckBox
import tm.com.balary.ui.ImageLoader

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CommentForm(
    show: Boolean,
    onDismiss: () -> Unit
) {
    val text = rememberSaveable {
        mutableStateOf("")
    }
    val showTerms = remember {
        mutableStateOf(false)
    }
    val launcher = rememberFilePickerLauncher(
        mode = PickerMode.Single,
        type = PickerType.ImageAndVideo
    ) { files ->
        println(files?.path)
    }

    ConstantSheet(
        show = showTerms.value,
        onDismiss = {
            showTerms.value = false
        }
    )
    if (show) {
        ModalBottomSheet(
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp,
            onDismissRequest = {
                onDismiss()
            },
            windowInsets = WindowInsets.statusBars
        ) {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    vertical = 12.dp,
                    horizontal = 16.dp
                )
            ) {
                stickyHeader {
                    Text(
                        "Bahalandyrmak",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                item {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RatingBar(
                            starSize = 36.dp,
                            spacing = 24.dp,
                            stars = 3.0
                        )
                    }
                }

                item {
                    Text(
                        "Önüm hakda pikiriňizi paýlaşmagyňyzy haýyş edýäris",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp
                        ),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                item {
                    OutlinedTextField(
                        value = text.value,
                        onValueChange = {
                            text.value = it
                        },
                        modifier = Modifier.fillMaxWidth().height(150.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                            unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                        placeholder = {
                            Text(
                                "Siziň teswiriňiz",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.outline
                            )
                        },
                        textStyle = MaterialTheme.typography.bodyLarge
                    )
                }

                item {
                    LazyRow(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(2) {
                            Box(Modifier.size(104.dp).clip(RoundedCornerShape(10.dp))) {
                                ImageLoader(
                                    modifier = Modifier.fillMaxSize(),
                                    url = "",
                                    contentScale = ContentScale.Crop,
                                    placeholder = painterResource(Res.drawable.banner)
                                )
                                Icon(
                                    painter = painterResource(Res.drawable.close_filled),
                                    contentDescription = "remove",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.align(Alignment.TopEnd).clip(CircleShape)
                                        .clickable {

                                        }.padding(8.dp)
                                )
                            }
                        }

                        item {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clip(RoundedCornerShape(10.dp)).clickable {
                                    launcher.launch()
                                }
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.baseline_camera_alt_24),
                                    contentDescription = "photo",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .size(52.dp)
                                        .background(
                                            color = MaterialTheme.colorScheme.primary,
                                            shape = CircleShape
                                        )
                                        .clip(CircleShape)
                                        .padding(13.dp)
                                )
                                Text(
                                    "Surat ýa-da wideoňyzy goşuň",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.W700,
                                    ),
                                    modifier = Modifier.width(100.dp),
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                            }
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(8.dp))
                }

                item {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AppCheckBox(
                            checked = true,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            "Teswir ýazmagyň şertleri bilen tanyşmak",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.W700,
                            ),
                            modifier = Modifier.weight(1f).clickable {
                                showTerms.value = true
                            },
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                item {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {

                        }
                    ) {
                        Text(
                            "Tassyklamak",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                item {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text(
                            "Goý bolsun",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                item {
                    Spacer(Modifier.height(22.dp))
                }
            }
        }
    }
}