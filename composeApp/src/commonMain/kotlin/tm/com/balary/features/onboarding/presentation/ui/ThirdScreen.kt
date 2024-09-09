package tm.com.balary.features.onboarding.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.second_image
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.auth.presentation.ui.AuthScreen
import tm.com.balary.features.home.presentation.ui.banner.LogoText
import tm.com.balary.router.AppTabScreen

@Composable
fun ThirdScreen() {
    val navigator = LocalNavigator.currentOrThrow
    Box(Modifier.fillMaxSize()) {
        GradientBackground(Modifier.fillMaxSize()) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(12.dp))
                Image(
                    painter = painterResource(Res.drawable.second_image),
                    contentDescription = "image",
                    modifier = Modifier.fillMaxWidth().height(332.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(Modifier.height(12.dp))
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LogoText(modifier = Modifier.width(170.dp).height(50.dp))
                    Text(
                        "Balary bilen islän harydyňyzy dükanlardan sargap bilersiňiz",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W900,
                            fontSize = 18.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Text(
                        "Biz bilen galyň we gündelik aksiýalarymyza we yaryşlarymyza gatnaşyp bal gazanyň!",
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700,
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.8f)

                    )

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            navigator.replaceAll(AppTabScreen())
                        }
                    ) {
                        Text(
                            "Dowam etmek",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            )
                        )
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            navigator.replaceAll(AuthScreen())
                        }
                    ) {
                        Text(
                            "Ulgama girmek",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            )
                        )
                    }
                }
            }
        }
    }
}