package tech.capitalcoding.pokedex.basic_feature.presentation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonsNavigationFactory
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonsUiState
import tech.capitalcoding.pokedex.core.navigation.NavigationFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PokemonsViewModelModule {
    @Provides
    fun provideInitialPokemonsUiState(): PokemonsUiState = PokemonsUiState()
}

@Module
@InstallIn(SingletonComponent::class)
internal interface PokemonsSingletonModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindPokemonsNavigationFactory(factory: PokemonsNavigationFactory): NavigationFactory
}