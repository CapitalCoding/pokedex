package tech.capitalcoding.pokedex.basic_feature.presentation
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.GetPokemonsUseCase
import tech.capitalcoding.pokedex.basic_feature.presentation.mapper.toDisplayModel
import tech.capitalcoding.pokedex.core.presentation.mvi.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    savedStateHandle: SavedStateHandle,
    pokemonsInitialState: PokemonsUiState
) : BaseViewModel<PokemonsUiState, PokemonsUiState.PartialState, PokemonsEvent, PokemonsIntent>(
    savedStateHandle = savedStateHandle,
    initialState = pokemonsInitialState
) {


    init {
        observePokemons()
    }

    private fun observePokemons() = acceptChanges(
        getPokemonsUseCase()
            .map {
                result ->
                    result.fold(
                        onSuccess = { pokemonsList ->
                            PokemonsUiState.PartialState.Fetched(pokemonsList.map { it.toDisplayModel() })
                        },
                        onFailure = {
                            PokemonsUiState.PartialState.Error(it)
                        }
                    )
            }
            .onStart {
                emit(PokemonsUiState.PartialState.Loading)
            }
    )


    override fun mapIntents(intent: PokemonsIntent): Flow<PokemonsUiState.PartialState> =
        when(intent){
            is PokemonsIntent.RefreshPokemons -> refreshPokemons()
            is PokemonsIntent.PokemonClicked -> pokemonClicked(1)
        }

    private fun pokemonClicked(i: Int): Flow<PokemonsUiState.PartialState> {
        TODO()
    }

    private fun refreshPokemons(): Flow<PokemonsUiState.PartialState> {
        TODO("Not yet implemented")
    }


    override fun reduceUiState(
        previousState: PokemonsUiState,
        partialState: PokemonsUiState.PartialState
    ): PokemonsUiState = when(partialState){
        is PokemonsUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false
        )
        is PokemonsUiState.PartialState.Fetched -> previousState.copy(
            isLoading = false,
            pokemons = partialState.list,
            isError = false
        )
        is PokemonsUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true
        )
    }

}

sealed class PokemonListEvent {
    object Load : PokemonListEvent()
}