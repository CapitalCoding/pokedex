package tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.GetPokemonByIdOrNameParams
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.GetPokemonByIdOrNameUseCase
import tech.capitalcoding.pokedex.basic_feature.presentation.mapper.toDisplayableModel
import tech.capitalcoding.pokedex.core.navigation.NavigationCommand
import tech.capitalcoding.pokedex.core.navigation.NavigationDestination
import tech.capitalcoding.pokedex.core.navigation.NavigationManager
import tech.capitalcoding.pokedex.core.presentation.mvi.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val useCase: GetPokemonByIdOrNameUseCase,
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    detailInitialState: DetailUiState
): BaseViewModel<DetailUiState, DetailUiState.PartialState, DetailViewEvent, DetailIntent>(
    initialState = detailInitialState,
    savedStateHandle = savedStateHandle,
) {

    private val pokemonId: String = checkNotNull(savedStateHandle.get<String>("pokemonId"))

    init {
        getPokemonDetails(pokemonId)
    }

    private fun getPokemonDetails(pokemonId: String) = acceptChanges(
        useCase.execute(GetPokemonByIdOrNameParams(idOrName = pokemonId))
            .map { result ->
                result.fold(
                    onSuccess = { pokemon ->
                        DetailUiState.PartialState.PokemonFetched(pokemon.toDisplayableModel())
                    },
                    onFailure = {
                        DetailUiState.PartialState.Error(it)
                    }
                )
            }
            .onStart {
                emit(DetailUiState.PartialState.Loading)
            }
    )

    override fun mapIntents(intent: DetailIntent): Flow<DetailUiState.PartialState> = when(intent) {
        is DetailIntent.ClickOnType -> goToType(intent.type)
    }

    private fun goToType(type: String): Flow<DetailUiState.PartialState> {
        return flow {
            navigationManager.navigate(
                object : NavigationCommand {
                    override val destination: String = NavigationDestination.Pokemons.route +"?type=" + type
                }
            )
        }
    }

    override fun reduceUiState(
        previousState: DetailUiState,
        partialState: DetailUiState.PartialState,
    ): DetailUiState = when (partialState) {
        is DetailUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false,
        )
        is DetailUiState.PartialState.PokemonFetched -> previousState.copy(
            isLoading = false,
            pokemon = partialState.pokemon,
            isError = false,
        )
        is DetailUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true
        )
    }

}