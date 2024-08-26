package tech.capitalcoding.pokedex.basic_feature.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import tech.capitalcoding.pokedex.basic_feature.presentation.model.PokemonDisplayable

@Immutable
@Parcelize
data class PokemonsUiState(
    val isLoading: Boolean = false,
    val pokemons: List<PokemonDisplayable> = emptyList(),
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState() // for simplicity: initial loading & refreshing

        data class Fetched(val list: List<PokemonDisplayable>) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}
