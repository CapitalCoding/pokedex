package tech.capitalcoding.pokedex.basic_feature.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tech.capitalcoding.pokedex.basic_feature.data.remote.api.PokemonApi
import tech.capitalcoding.pokedex.basic_feature.data.repository.PokemonRepositoryImpl
import tech.capitalcoding.pokedex.basic_feature.domain.repository.PokemonRepository
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.GetPokemonsUseCase
import tech.capitalcoding.pokedex.basic_feature.domain.usecase.getPokemons
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PokemonModule {

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    @Provides
    fun provideGetPokemonsUseCase(pokemonRepository: PokemonRepository): GetPokemonsUseCase {
        return GetPokemonsUseCase {
            getPokemons(pokemonRepository)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository
    }
}