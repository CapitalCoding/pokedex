package tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.search

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import tech.capitalcoding.pokedex.basic_feature.domain.model.SearchTypeListResult
import tech.capitalcoding.pokedex.basic_feature.presentation.model.DisplayableType

@Immutable
@Parcelize
data class SearchUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val pokemonTypes: SearchTypeListResult<DisplayableType> = SearchTypeListResult.empty(),
): Parcelable {
    sealed class PartialState {
        data object Loading : PartialState() // for simplicity: initial loading & refreshing
        data class Error(val throwable: Throwable) : PartialState()
        data class PokemonTypesFetched(val types: SearchTypeListResult<DisplayableType>) : PartialState()
    }
}