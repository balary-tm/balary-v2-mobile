package tm.com.balary.features.onboarding.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.third_image
import balary.composeapp.generated.resources.watch
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.home.presentation.ui.banner.LogoText

@Composable
fun SecondScreen(
    onNext: ()-> Unit
) {
    Box(Modifier.fillMaxSize()) {
        GradientBackground(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(Res.drawable.third_image),
                    contentDescription = "fruits",
                    modifier = Modifier.fillMaxWidth().height(360.dp).align(Alignment.BottomStart),
                    contentScale = ContentScale.Crop
                )
                Column(
                    Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LogoText(modifier = Modifier.width(170.dp).height(50.dp))
                        Text(
                            "Biziň  hyzmatymyzyň Size berýän peýdalary",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W900,
                                fontSize = 18.sp
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(0.6f)
                        )
                        Text(
                            "1. Islendik ýerden sargyt etmek\n" +
                                    "2. Wagtynyzy tygşytlamak\n" +
                                    "3. Agyr harytlary götermezlik\n" +
                                    "4. Dykynlarda wagt sarp edip durmazlyk\n" +
                                    "5. Ýokary hilli harytlary Size eltip bermek",
                            color = MaterialTheme.colorScheme.outline,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700,
                            ),
                            textAlign = TextAlign.Center

                        )

                        Image(
                            painter = painterResource(Res.drawable.watch),
                            contentDescription = "watch",
                            modifier = Modifier.size(56.dp)
                        )
                        Text(
                            "Gymmatly wagtyňyzy özüňize we ýakynlaryňyza sarp edin!\n" +
                                    "Goý Balary marketpleýs her bir öýe şatlyk getirsin!",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W700
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }



                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onNext()
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
                }
            }
        }
    }
}