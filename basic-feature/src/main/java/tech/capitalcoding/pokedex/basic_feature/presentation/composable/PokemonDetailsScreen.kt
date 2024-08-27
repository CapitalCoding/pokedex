import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import tech.capitalcoding.basic_feature.R
import tech.capitalcoding.pokedex.basic_feature.presentation.composable.ErrorScreen
import tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.details.DetailIntent
import tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.details.DetailUiState
import tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.details.DetailViewModel

@Composable
fun PokemonDetailRoute(viewModel: DetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    DetailsScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun DetailsScreen(
    uiState: DetailUiState,
    onIntent: (DetailIntent) -> Unit
) {
    Scaffold { paddingValues ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.isError -> {
                ErrorScreen()
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = uiState.pokemon.name, style = MaterialTheme.typography.titleLarge)
                                Spacer(modifier = Modifier.height(16.dp))
                                Image(
                                    painter = rememberImagePainter(uiState.pokemon.spriteNormalFront),
                                    contentDescription = "${uiState.pokemon.name} Normal Sprite",
                                    modifier = Modifier.size(200.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = "ID: ${uiState.pokemon.id}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Height: ${uiState.pokemon.height}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Weight: ${uiState.pokemon.weight}", style = MaterialTheme.typography.bodyLarge)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = "Types:", style = MaterialTheme.typography.bodyLarge)
                                Row {
                                    uiState.pokemon.types.forEach { type ->
                                        AssistChip(
                                            onClick = { onIntent(DetailIntent.ClickOnType(type.name)) },
                                            label = { Text(text = type.name) },
                                            modifier = Modifier.padding(4.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = "Stats:", style = MaterialTheme.typography.bodyLarge)
                                Column(
                                    modifier = Modifier
                                        .animateContentSize()
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "Speed: ${uiState.pokemon.speed}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Defense: ${uiState.pokemon.defence}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Attack: ${uiState.pokemon.attack}", style = MaterialTheme.typography.bodyLarge)
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(uiState.pokemon.spriteShinyFront)
                                        .crossfade(true)
                                        .placeholder(R.drawable.loading_pokemon_placeholder)
                                        .error(R.drawable.pokemon_placeholder)
                                        .build(),
                                    contentDescription = "${uiState.pokemon.name} Shiny Sprite",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clip(MaterialTheme.shapes.medium)
                                )
                            }

                    }
                }
            }
        }
        }
        }
