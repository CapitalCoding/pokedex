package tech.capitalcoding.pokedex.basic_feature.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import tech.capitalcoding.pokedex.basic_feature.presentation.model.DisplayableType
import tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.search.SearchEvent
import tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.search.SearchIntent
import tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.search.SearchUiState
import tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.search.SearchViewModel
import tech.capitalcoding.pokedex.core.navigation.NavigationDestination
import tech.capitalcoding.pokedex.core.utils.collectWithLifecycle

@Composable
fun SearchRoute(viewModel: SearchViewModel = hiltViewModel()) {
    HandleEvents(viewModel.getEvents())
    val uiState by viewModel.uiState.collectAsState()

    SearchScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onIntent: (SearchIntent) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var selectedTabIndex by remember { mutableStateOf(0) }
    var selectedType by remember { mutableStateOf(DisplayableType("", "")) }

    val tabTitles = listOf("Name", "ID", "Type")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            when (selectedTabIndex) {
                0 -> TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                1 -> TextField(
                    value = id,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            id = it
                        }
                    },
                    label = { Text("ID") },
                    modifier = Modifier.fillMaxWidth()
                )
                2 -> Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(uiState.pokemonTypes.items) { pokemonType ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = selectedType == pokemonType,
                                    onClick = { selectedType = pokemonType }
                                )
                                Text(
                                    text = pokemonType.name,
                                    modifier = Modifier.padding(start = 8.dp)
                                        .clickable { selectedType = pokemonType }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        uiState.pokemonTypes.previousOffset?.let {
                            Button(
                                onClick = { onIntent(SearchIntent.LoadPreviousPage(it)) },
                            ) {
                                Text("Previous")
                            }
                        }
                        uiState.pokemonTypes.nextOffset?.let {
                            Button(
                                onClick = { onIntent(SearchIntent.LoadNextPage(it)) },
                            ) {
                                Text("Next")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            onIntent(SearchIntent.SearchByType(selectedType.name))
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Filter")
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (selectedTabIndex != 2) {
                Button(
                    onClick = {
                        when (selectedTabIndex) {
                            0 -> onIntent(SearchIntent.SearchByName(name))
                            1 -> onIntent(SearchIntent.SearchById(id))
                            2 -> onIntent(SearchIntent.SearchByType(selectedType.name))
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Filter")
                }
            }
        }
    }
}

@Composable
private fun HandleEvents(events: Flow<SearchEvent>) {
//    val navController = rememberNavController()
//    events.collectWithLifecycle {
//        when (it) {
//            is SearchEvent.OpenPokemonListWithId -> navController.navigate(NavigationDestination.Pokemons.route +"/id="+ it.id)
//            is SearchEvent.OpenPokemonListWithType -> navController.navigate(NavigationDestination.Pokemons.route +"/type="+ it.type)
//            is SearchEvent.openPokemonListWithName -> navController.navigate(NavigationDestination.Pokemons.route +"?name="+ it.name)
//        }
//    }
}