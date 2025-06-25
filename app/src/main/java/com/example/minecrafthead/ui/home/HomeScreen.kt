package com.example.minecrafthead.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.minecrafthead.R
import com.example.minecrafthead.navigation.Screen
import com.example.minecrafthead.utils.Resource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {
    var username by remember { mutableStateOf("") }
    val avatarState by viewModel.avatar.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Favorites.route)
                    }) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = stringResource(R.string.favorites_title)
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.enter_pseudo),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(R.string.pseudo)) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.loadAvatar(username) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(stringResource(R.string.show_avatar), fontSize = 16.sp)
            }

            when (val state = avatarState) {
                is Resource.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Resource.Success -> {
                    val bitmap = state.data
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(R.string.avatar_for, username),
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "Avatar",
                                modifier = Modifier.size(128.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    viewModel.addFavorite(username)
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Ajouté à vos favoris !")
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                enabled = !favorites.contains(username) && favorites.size < viewModel.maxFavorites
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = stringResource(R.string.add_to_favorites)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(stringResource(R.string.add_to_favorites))
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    Text(
                        text = stringResource(R.string.error_message, state.message),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                }

                else -> {}
            }
        }
    }
}
