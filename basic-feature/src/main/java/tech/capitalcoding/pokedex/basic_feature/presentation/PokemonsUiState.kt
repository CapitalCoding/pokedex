package tech.capitalcoding.pokedex.basic_feature.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonListResult
import tech.capitalcoding.pokedex.basic_feature.presentation.model.PokemonDisplayable

@Immutable
@Parcelize
data class PokemonsUiState(
    val isLoading: Boolean = false,
    val pokemons: PokemonListResult<PokemonDisplayable> = PokemonListResult.empty(),
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState() // for simplicity: initial loading & refreshing

        data class Fetched(val list: PokemonListResult<PokemonDisplayable>) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}
