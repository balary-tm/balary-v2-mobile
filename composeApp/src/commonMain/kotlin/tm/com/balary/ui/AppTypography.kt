package tm.com.balary.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.alsschlangesans
import balary.composeapp.generated.resources.alsschlangesans_black
import balary.composeapp.generated.resources.alsschlangesans_bold
import org.jetbrains.compose.resources.Font

@Composable
fun AlsFontFamily() = FontFamily(
    Font(Res.font.alsschlangesans, weight = FontWeight.Light),
    Font(Res.font.alsschlangesans, weight = FontWeight.Normal),
    Font(Res.font.alsschlangesans, weight = FontWeight.Medium),
    Font(Res.font.alsschlangesans, weight = FontWeight.SemiBold),
    Font(Res.font.alsschlangesans_bold, weight = FontWeight.Bold),
    Font(Res.font.alsschlangesans_bold, weight = FontWeight.ExtraBold),
    Font(Res.font.alsschlangesans, weight = FontWeight.ExtraLight),
    Font(Res.font.alsschlangesans_black, weight = FontWeight.Black),
)