package tech.capitalcoding.pokedex.core.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.capitalcoding.pokedex.core.navigation.NavigationDestination
import tech.capitalcoding.pokedex.core.navigation.NavigationFactory
import tech.capitalcoding.pokedex.core.navigation.NavigationHost
import tech.capitalcoding.pokedex.core.navigation.NavigationManager
import tech.capitalcoding.pokedex.core.design.OnPrimaryColor
import tech.capitalcoding.pokedex.core.design.PokedexTheme
import tech.capitalcoding.pokedex.core.design.PrimaryColor
import tech.capitalcoding.pokedex.core.utils.collectWithLifecycle
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var navigationManager: NavigationManager

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        PokemonTopBar(
                            onSearchClick = { navController.navigate(NavigationDestination.Search.route) },
                        ) {
                            navController.navigate(NavigationDestination.Pokemons.route)
                        }
                    }
                ) {
                    NavigationHost(
                        modifier = Modifier
                            .padding(it),
                        navController = navController,
                        factories = navigationFactories,
                    )
                }

                navigationManager
                    .navigationEvent
                    .collectWithLifecycle(
                        key = navController,
                    ) {
                        when (it.destination) {
                            NavigationDestination.Back.route -> navController.navigateUp()
                            else -> navController.navigate(it.destination, it.configuration)
                        }
                    }
            }
        }
    }
}

@Composable
fun PokemonTopBar(onSearchClick: () -> Unit, onTitleClick: () -> Unit) {
    TopAppBar(
        title = { Text(
            text = "Pokédex",
            modifier = Modifier.clickable { onTitleClick() }
        ) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryColor,
            titleContentColor = OnPrimaryColor
        ),
        actions = {
            IconButton(
                onClick = onSearchClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(Icons.Outlined.Search, contentDescription = "Search")
            }
        },
        modifier = Modifier.height(56.dp)
    )
}

