package tech.capitalcoding.pokedex.basic_feature.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.capitalcoding.pokedex.basic_feature.presentation.model.PokemonDisplayable


@Composable
fun PokemonListContent(
    items: List<PokemonDisplayable>,
    onPokemonItemClick: (PokemonDisplayable) -> Unit,
    onPreviousPageClick: () -> Unit,
    onNextPageClick: () -> Unit,
    showPreviousPageBtn: Boolean,
    showNextPageBtn: Boolean,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
        ) {
            itemsIndexed(
                items = items,
                key = { _, pokemon -> pokemon.id },
            ) { _, item ->
                PokemonItem(
                    pokemon = item,
                    onPokemonClick = { onPokemonItemClick(item) },
                )
            }
            item {
                Spacer(modifier = Modifier.height(66.dp))
            }
        }

        if (showPreviousPageBtn) {
            OutlinedButton(
                onClick = onPreviousPageClick,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Previous")
            }
        }

        if (showNextPageBtn) {
            OutlinedButton(
                onClick = onNextPageClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Next")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
            }
        }
    }
}