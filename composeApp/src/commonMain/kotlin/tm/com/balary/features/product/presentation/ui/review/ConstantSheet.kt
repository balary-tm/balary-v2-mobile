package tm.com.balary.features.product.presentation.ui.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.lyricist.LocalStrings
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstantSheet(
    show: Boolean,
    onDismiss: () -> Unit
) {
    val strings = LocalStrings.current
    if (show) {
        ModalBottomSheet(
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp,
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Column(
                Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    strings.termsOfCommentWrite,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )

                WebView(
                    modifier = Modifier.weight(1f),
                    state = rememberWebViewStateWithHTMLData(
                        data = """Eltip berme hyzmaty diňe Aşgabat şäheriniň içinde amala aşyrylýar.
Eltip berme hyzmatynyň bahasy ýerleşýän ýeriňize görä üýtgeýär.
200 manatdan ýokary söwda eden her bir müşderä eltip bermek hyzmaty MUGT!
Harytlary alanyňyzdan soň töleg geçirilýär.
Töleg görnüşleri
Sargyt edilen harytlary islendik görnüşde töläp bilersiňiz:
Nagt görnüşinde. Sargyt (bellenilen salga getirilenden) eltilenden soň harytlar bilen doly tanyşyp nagt görnüşde töleg edilýär.
Plastik kart üsti bilen. Edilen sargytlary plasik kart üsti bilen töleg edip bilersiňiz. Biz "Altyn Asyr" plastik kartyny kabul edýäris.
ÜNS BERIŇ! Eger-de dürli sebäplere görä siziň kartyňyzdan töleg geçirilmedik ýagdaýynda, NAGT görnüşinde töleg amala aşyrylmalydygyny duýdurýarys!"""
                    )
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {

                    }
                ) {
                    Text(
                        strings.readIt,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(Modifier.height(22.dp))

            }
        }
    }
}