package tm.com.balary.features.contact.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.emoji
import balary.composeapp.generated.resources.photo
import balary.composeapp.generated.resources.send
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import tm.com.balary.features.auth.presentation.ui.BackScreen
import tm.com.balary.features.contact.presentation.viewmodel.ChatViewModel

class ChatScreen : Screen {
    @Composable
    override fun Content() {

    }
}

@Composable
fun Chats(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val message = rememberSaveable {
        mutableStateOf("")
    }
    val strings = LocalStrings.current
    val chatViewModel: ChatViewModel = koinViewModel()
    val chatState = chatViewModel.chat.collectAsState()
    LaunchedEffect(true) {
        chatViewModel.getChats()

    }
    BackScreen(Modifier.fillMaxSize().imePadding(), title = strings.contactWithMe, navHostController = navHostController) {
        Column(
            Modifier.fillMaxSize().imePadding(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            LazyColumn(
                Modifier.fillMaxWidth().weight(1f).background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ), contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                if (chatState.value.loading) {
                    item {
                        LinearProgressIndicator(Modifier.fillMaxWidth())
                    }
                } else if(chatState.value.error!=null) {
                    item {
                        Text("Something went wrong")
                    }
                } else if(chatState.value.chats.isNullOrEmpty().not()) {
                    chatState.value.chats?.let { list->
                        items(list.count()) {index->
                            ChatItem(
                                modifier = Modifier.fillMaxWidth(),
                                item = list[index],
                                lastItem = if(index>0) list[index-1] else null
                            )
                        }
                    }
                }

            }

            Row(
                Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ).padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.photo),
                        contentDescription = "select photo",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                TextField(
                    value = message.value,
                    onValueChange = {
                        message.value = it
                    },
                    shape = RoundedCornerShape(4.dp),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {

                        }
                    ),
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        errorContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            strings.writeHere,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.outline
                        )
                    },
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.emoji),
                                contentDescription = "emoji select",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                                }.padding(2.dp)
                            )

                            Icon(
                                painter = painterResource(Res.drawable.send),
                                contentDescription = "send message",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                                }.padding(2.dp)
                            )
                            Spacer(Modifier.height(4.dp))
                        }
                    }
                )
            }

        }

    }
}