package tm.com.balary.features.privacy.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import tm.com.balary.features.auth.presentation.ui.BackScreen

class PrivacyScreen : Screen {
    @Composable
    override fun Content() {
        BackScreen(Modifier.fillMaxSize(), title = "Ulanyş düzgünleri") {
            Column(
                modifier = Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ).padding(16.dp))
            {
                WebView(
                    modifier = Modifier.fillMaxSize(),
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
            }
        }
    }
}