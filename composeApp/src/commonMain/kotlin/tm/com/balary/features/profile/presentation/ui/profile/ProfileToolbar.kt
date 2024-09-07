package tm.com.balary.features.profile.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.edit
import balary.composeapp.generated.resources.profile
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.home.presentation.ui.banner.LogoText
import tm.com.balary.features.profile.presentation.ui.edit.EditProfileScreen
import tm.com.balary.state.LocalAuth

@Composable
fun ProfileToolbar(
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow
    val authState = LocalAuth.current
    val shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)
    Column(
        modifier = modifier.background(Color.Transparent).background(
            color = MaterialTheme.colorScheme.tertiary,
            shape = shape
        ).clip(shape).padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            LogoText(
                modifier = Modifier.offset(y = 16.dp).width(170.dp).height(50.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(Modifier.height(8.dp))

        if (authState.value.logged) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileAvatar()
                Row(
                    Modifier.weight(1f).border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(10.dp)
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Balary",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                        )

                        HorizontalDivider(
                            Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.outline
                        )

                        Text(
                            "+993 62 623698",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W400,
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                        )


                    }

                    Box(Modifier.defaultMinSize(minHeight = 65.dp).background(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = RoundedCornerShape(
                            topEnd = 10.dp,
                            bottomEnd = 10.dp
                        )
                    ).clip(RoundedCornerShape(
                        topEnd = 10.dp,
                        bottomEnd = 10.dp
                    )).clickable {
                        navigator.push(EditProfileScreen())
                    }.padding(2.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(Res.drawable.edit),
                            contentDescription = "edit",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        } else {
            Text(
                "Ulgama giriň \n" +
                        "ýa-da agza boluň",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W900
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                "Söwda etmek üçin ýa-da eden söwdaňyzy yzarlamak üçin",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                ),
                onClick = {

                }
            ) {
                Text(
                    "Ulgama girmek ýa-da agza bolmak",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}