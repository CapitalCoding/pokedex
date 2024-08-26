package tech.capitalcoding.pokedex.core.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import tech.capitalcoding.pokedex.core.design.OnPrimaryColor
import tech.capitalcoding.pokedex.core.design.PokedexTheme
import tech.capitalcoding.pokedex.core.design.PrimaryColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                Scaffold(
                    topBar = { PokemonTopBar() }
                ) {
                    Text("Hello, Pokedex!")
                }
            }
        }
    }
}

@Composable
fun PokemonTopBar() {
    TopAppBar(
        title = { Text("Pokédex") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryColor,
            titleContentColor = OnPrimaryColor
        ),
        modifier = Modifier.height(56.dp)
    )
}

