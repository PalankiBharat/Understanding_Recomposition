package com.example.understandingrecomposition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.understandingrecomposition.ui.theme.UnderstandingRecompositionTheme

class MainActivity : ComponentActivity() {
    private val viewModel = MyViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnderstandingRecompositionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = viewModel.uiStates.collectAsState().value

                    Column {
                        NewScreen(state = state){
                            viewModel.changeEmail()
                        }

                        Button(onClick = { viewModel.changeEmail() }) {
                            Text(text = "Change Email")
                        }

                        Button(onClick = { viewModel.updateLoading() }) {
                            Text(text = "Change Loading")
                        }

                        Button(onClick = { viewModel.changeUser() }) {
                            Text(text = "Change Total User")
                        }

                        Button(onClick = { viewModel.changeUserNameOnly() }) {
                            Text(text = "Change User Name only")
                        }

                        Button(onClick = { viewModel.changelistRandomly() }) {
                            Text(text = "Change List Randomly")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun NewScreen(state: SuperheroListingUIStates, onClick:()->Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = state.email, fontSize = 20.sp)

        // The above one and below one are same but the below one will recompose everytime as it has the clickable modifier.
        Text(modifier = Modifier.clickable {

        }, text = state.email, fontSize = 20.sp)
        Button(onClick = {onClick() }) {
            Text(text = "Click Me", fontSize = 20.sp)
        }
        Text(text = state.loading.toString(), fontSize = 20.sp)
        Text(text = state.user.name, fontSize = 20.sp)
        Text(text = state.user.roll, fontSize = 20.sp)
        Text(text = "List = ${state.list}", fontSize = 20.sp)
    }
}
