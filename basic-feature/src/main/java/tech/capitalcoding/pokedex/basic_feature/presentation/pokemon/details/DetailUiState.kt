package tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.details

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import tech.capitalcoding.pokedex.basic_feature.presentation.model.DisplayablePokemonDetails

@Immutable
@Parcelize
data class DetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val pokemon: DisplayablePokemonDetails = DisplayablePokemonDetails.empty(),
): Parcelable {
    sealed class PartialState {
        data object Loading : PartialState()
        data class Error(val error: Throwable) : PartialState()
        data class PokemonFetched(val pokemon: DisplayablePokemonDetails) : PartialState()
    }
}