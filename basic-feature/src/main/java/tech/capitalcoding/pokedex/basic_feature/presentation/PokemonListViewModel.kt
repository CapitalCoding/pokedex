package tech.capitalcoding.pokedex.basic_feature.presentation
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.GetPokemonsParams
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.GetPokemonsUseCase
import tech.capitalcoding.pokedex.basic_feature.presentation.mapper.toDisplayModel
import tech.capitalcoding.pokedex.core.BuildConfig
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
        getPokemonsUseCase.execute(GetPokemonsParams(limit = 20, offset = 0))
            .map {
                result ->
                    result.fold(
                        onSuccess = { pokemonsList ->
                            PokemonsUiState.PartialState.Fetched(pokemonsList.toDisplayModel())
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
            is PokemonsIntent.PokemonClicked -> refreshPokemons()
            is PokemonsIntent.LoadMorePokemons -> nextPokemonsPage(intent.offset)
            is PokemonsIntent.PreviousPage -> previousPokemonsPage(intent.offset)
        }

    private fun previousPokemonsPage(offset: Int): Flow<PokemonsUiState.PartialState> {
        return getPokemonsUseCase.execute(GetPokemonsParams(limit = 20, offset = offset))
            .map { result ->
                result.fold(
                    onSuccess = { pokemonsList ->
                        PokemonsUiState.PartialState.Fetched(pokemonsList.toDisplayModel())
                    },
                    onFailure = {
                        PokemonsUiState.PartialState.Error(it)
                    }
                )
            }
            .onStart {
                emit(PokemonsUiState.PartialState.Loading)
            }
    }


    private fun refreshPokemons(): Flow<PokemonsUiState.PartialState> {
        return flow {  }
    }

    private fun nextPokemonsPage(offset: Int): Flow<PokemonsUiState.PartialState> {
        return getPokemonsUseCase.execute(GetPokemonsParams(limit = 20, offset = offset))
            .map { result ->
                result.fold(
                    onSuccess = { pokemonsList ->
                        PokemonsUiState.PartialState.Fetched(pokemonsList.toDisplayModel())
                    },
                    onFailure = {
                        PokemonsUiState.PartialState.Error(it)
                    }
                )
            }
            .onStart {
                emit(PokemonsUiState.PartialState.Loading)
            }
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
            isError = false,
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