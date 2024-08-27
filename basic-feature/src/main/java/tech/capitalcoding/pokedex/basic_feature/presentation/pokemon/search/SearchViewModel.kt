package tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.search

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.GetPokemonTypeParams
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.GetPokemonTypeUseCase
import tech.capitalcoding.pokedex.basic_feature.presentation.mapper.toDisplayableModel
import tech.capitalcoding.pokedex.core.navigation.NavigationCommand
import tech.capitalcoding.pokedex.core.navigation.NavigationDestination
import tech.capitalcoding.pokedex.core.navigation.NavigationManager
import tech.capitalcoding.pokedex.core.presentation.mvi.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: GetPokemonTypeUseCase,
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    searchInitialState: SearchUiState
): BaseViewModel<SearchUiState, SearchUiState.PartialState, SearchEvent, SearchIntent>(
    savedStateHandle = savedStateHandle,
    initialState = searchInitialState
) {

    init {
        retrieveTypes()
    }

    private fun retrieveTypes() = acceptChanges(
         searchUseCase.execute(GetPokemonTypeParams(offset = 0, limit = 30))
            .map { result ->
                result.fold(
                    onSuccess = { types ->
                        SearchUiState.PartialState.PokemonTypesFetched(types.toDisplayableModel())
                    },
                    onFailure = {
                        SearchUiState.PartialState.Error(it)
                    }
                )
            }
            .onStart {
                emit(SearchUiState.PartialState.Loading)
            }
)

    override fun mapIntents(intent: SearchIntent): Flow<SearchUiState.PartialState> {
        return when(intent) {
            is SearchIntent.SearchByType -> searchByType(intent.type)
            is SearchIntent.SearchById -> searchById(intent.id)
            is SearchIntent.SearchByName -> searchByName(intent.name)
            is SearchIntent.LoadNextPage -> loadNextPage(intent.nextOffset)
            is SearchIntent.LoadPreviousPage -> loadPreviousPage(intent.previousOffset)
        }
    }

    private fun searchById(id: String): Flow<SearchUiState.PartialState> {
        return flow {
            navigationManager.navigate(
                object : NavigationCommand {
                    override val destination: String = NavigationDestination.PokemonDetails.route +"/"+ id
                }
            )
        }
    }

    private fun searchByName(name: String): Flow<SearchUiState.PartialState> {
        return flow {
            navigationManager.navigate(
                object : NavigationCommand {
                    override val destination: String = NavigationDestination.PokemonDetails.route +"/"+ name
                }
            )
        }
    }

    private fun loadPreviousPage(previousOffset: Int): Flow<SearchUiState.PartialState> {
        return flow {
            searchUseCase.execute(GetPokemonTypeParams(offset = previousOffset, limit = 30))
                .map { result ->
                    result.fold(
                        onSuccess = { types ->
                            SearchUiState.PartialState.PokemonTypesFetched(types.toDisplayableModel())
                        },
                        onFailure = {
                            SearchUiState.PartialState.Error(it)
                        }
                    )
                }
                .onStart {
                    emit(SearchUiState.PartialState.Loading)
                }
        }
    }

    private fun loadNextPage(nextOffset: Int): Flow<SearchUiState.PartialState> {
        return flow {
            searchUseCase.execute(GetPokemonTypeParams(offset = nextOffset, limit = 30))
                .map { result ->
                    result.fold(
                        onSuccess = { types ->
                            SearchUiState.PartialState.PokemonTypesFetched(types.toDisplayableModel())
                        },
                        onFailure = {
                            SearchUiState.PartialState.Error(it)
                        }
                    )
                }
                .onStart {
                    emit(SearchUiState.PartialState.Loading)
                }
        }
    }

    private fun searchByType(type: String): Flow<SearchUiState.PartialState> {
        return flow {
            navigationManager.navigate(
                object : NavigationCommand {
                    override val destination: String = NavigationDestination.Pokemons.route +"?type="+ type
                }
            )
        }
    }

    override fun reduceUiState(
        previousState: SearchUiState,
        partialState: SearchUiState.PartialState
    ): SearchUiState {
        return when(partialState) {
            is SearchUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false
            )
            is SearchUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true
            )
            is SearchUiState.PartialState.PokemonTypesFetched -> previousState.copy(
                isLoading = false,
                isError = false,
                pokemonTypes = partialState.types
            )
        }
    }
}