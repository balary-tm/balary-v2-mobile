package tm.com.balary.features.about.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import com.mikepenz.markdown.m3.Markdown
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import tm.com.balary.features.auth.presentation.ui.BackScreen
import tm.com.balary.ui.AlsFontFamily
import tm.com.balary.ui.AppAlert
import tm.com.balary.ui.AppAlertType

class AboutScreen : Screen {
    @Composable
    override fun Content() {

    }
}

@Composable
fun About(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val strings = LocalStrings.current
    BackScreen(Modifier.fillMaxSize(), title = strings.aboutUs, navHostController = navHostController) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                AboutLink(
                    modifier = Modifier.fillMaxWidth(),
                    title = strings.ourPhoneNumber,
                    value = "+993 62 073830"
                )

                AboutLink(
                    modifier = Modifier.fillMaxWidth(),
                    title = strings.ourPhoneNumber,
                    value = "+993 61 470939"
                )

                AboutLink(
                    modifier = Modifier.fillMaxWidth(),
                    title = strings.email,
                    value = "info@balary.net"
                )

                AboutLink(
                    modifier = Modifier.fillMaxWidth(),
                    title = strings.workHours,
                    value = "09:00 - 21:00"
                )

            }

            Column(
                modifier = Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ).padding(16.dp))
            {
//                        WebView(
//                            modifier = Modifier.fillMaxSize(),
//                            state = rememberWebViewStateWithHTMLData(
//                                data = """«Balary» — Aşgabatdaky onlaýn-supermarket, harytlary öýüňize ýa-da ofisiňize eltip bermek hyzmaty. Biziň internet-magazinimiz azyk harytlaryň we beýleki harytlaryň görnüşlerini uly saýlawyny hödürleýär. Her bir atlandrylan haryt pugta hil barlagyndan geçirilýär.
//
//«Balary» harytlary sargamak üçin amatly wagt araçäkleri, şol sanda sargyt edilen güni eltip bermegi bilen ýokary derejeli hyzmaty kepillendirýär.
//
//Satyn alynan harytlary internet-magazinimiziň web-sahypasynda, telefon programmasyda ýa-da telefon arkalyy öýüňize eltip bermek hyzmaty bilen sargyt edip bilersiňiz: +993 (12) 92 40 71.
//
//Balary – bu diňe bir ter önümler bolman, hemişe gyzykly aksiýalar we haýyrly hödürlenmeler, möwsümleýin saýlanan harytlar bolup durýar!
//
//Biziň salgymyz: Türkmenistan, Aşgabat ş. , 2028 köçe (Ata Gowşudow), jaý 47 «A» 1-nji gat"""
//                            )
//                        )

                val markdown = """
                            ### What's included 🚀
                            
                            - Super simple setup
                            - Cross-platform ready
                            - Lightweight
                            """.trimIndent()

                //
                Markdown(markdown)
            }
        }
    }
}

@Composable
fun AboutLink(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
) {
    val show = remember {
        mutableStateOf(false)
    }
    val strings = LocalStrings.current

    AppAlert(
        show = show.value,
        onDismiss = {
            show.value = false
        },
        title = strings.attention,
        type = AppAlertType.INFO,
        message = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontFamily = AlsFontFamily(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
            ) {
                append(strings.redirecting)
            }

            withStyle(
                SpanStyle(
                    fontFamily = AlsFontFamily(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            ) {
                append(value)
            }

        }
    )
    Row(
        modifier = modifier.clickable {
            show.value = true
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            value,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.W700
            )
        )
    }
}