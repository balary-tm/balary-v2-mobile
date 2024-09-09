package tm.com.balary.features.onboarding.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.first_bg
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.home.presentation.ui.banner.LogoText

@Composable
fun FirstScreen(
    onNext: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.first_bg),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            Modifier.fillMaxWidth().fillMaxHeight(0.7f).align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoText(modifier = Modifier.width(170.dp).height(50.dp))
                Text(
                    "HOŞ GELDIŇIZ",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.W900,
                        fontSize = 36.sp
                    ),
                    color = MaterialTheme.colorScheme.error
                )
                Image(
                    painter = painterResource(Res.drawable.banner),
                    contentDescription = "certificate",
                    modifier = Modifier.width(105.dp).height(162.dp),
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    "Balary Marketpleýs iň öňde barýan ýokary hilli platforma bolup Döwrabap hyzmatlaryň iň naýbaşy görnüşini Size hödürleýar!",
                    color = Color(0xFF614FE0),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 22.dp)
                )

                Text(
                    "Döwlet tarapyndan tassyklanan",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.W700
                    ),
                    textAlign = TextAlign.Center
                )

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

                Spacer(Modifier.height(22.dp))
            }

        }
    }
}