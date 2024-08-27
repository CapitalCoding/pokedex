package tech.capitalcoding.pokedex.basic_feature.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import tech.capitalcoding.pokedex.basic_feature.presentation.model.EmptyPokemonList
import tech.capitalcoding.pokedex.basic_feature.presentation.model.PokemonList

@Immutable
@Parcelize
data class PokemonsUiState(
    val isLoading: Boolean = false,
    val pokemons: @RawValue PokemonList = EmptyPokemonList.empty(),
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState() // for simplicity: initial loading & refreshing

        data class Fetched(val list: PokemonList) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}
