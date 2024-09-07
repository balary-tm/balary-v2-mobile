package tm.com.balary.features.payment.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.bag
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.honey
import cafe.adriel.voyager.core.screen.Screen
import coil3.ImageLoader
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.auth.presentation.ui.BackScreen

class PaymentScreen : Screen {
    @Composable
    override fun Content() {
        BackScreen(Modifier.fillMaxSize(), title = "Meniň hasabym") {
            Column(
                Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth().background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    ).padding(16.dp)
                ) {
                    Row(
                        Modifier.background(
                            color = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.6f
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ).clip(RoundedCornerShape(10.dp)).padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.honey),
                            contentDescription = "mark",
                            modifier = Modifier.size(36.dp)
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "0 bal ",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.W700,
                                    fontSize = 18.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                "1 bal=1TMT",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                tm.com.balary.ui.ImageLoader(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp))
                        .height(140.dp),
                    url = "",
                    placeholder = painterResource(Res.drawable.banner),
                    contentScale = ContentScale.FillBounds
                )

                Column(
                    modifier = Modifier.fillMaxWidth().background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    ).padding(16.dp)
                ) {
                    // put webview
                    val state = rememberWebViewStateWithHTMLData(
                        data = """
                            <h3 style='color: #614FE0;'>Bal gazanmagyñ we ulanmagyñ şertleri:</h3>
                            <p>Bal gazanmagyñ şertleri:
                            Bal gazanmak üçin Balary tarapyndan hödürlenÿän dürli görnüşde aksiÿalar we ÿaryşlar geçirilÿär. Siz şol aksiÿalara we ÿaryşlara gatnaşyp köp sanly bal gazanyp bilersiñiz! 
                            Aksiÿalar we ÿaryşlar belli bir wagt dowam edip biler!
                            1bal=1TMT deñdir!</p>
                            <br/>

                            <h3 style='color: #614FE0;'>Gazanylan ballaryñ ulanyş şertleri:</h3>
                            <p>Gazanylan ballary diñe Balary platformasyndan söwda edip ulanyp bilersiñiz.
                            Gazanan ballaryñyz pesinden 50-bal bolmaly.
                            Gazanylan ballara 6 aÿ möhlet berilÿär. Ulanylmadyk ballar 6 aýdan soñ öz güÿjüni ÿitirÿär!
                            Gazanylan Ballar 24 sagadyň dowamynda Admistrasiýa tarapyndan barlanyp geçiriler!</p>
                        """.trimIndent()
                    )
                    WebView(
                        modifier = Modifier.fillMaxWidth(),
                        state = state
                    )
                }

                Spacer(Modifier.height(8.dp))
            }


        }
    }
}