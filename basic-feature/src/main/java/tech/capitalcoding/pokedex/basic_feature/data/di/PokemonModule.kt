package tech.capitalcoding.pokedex.basic_feature.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tech.capitalcoding.pokedex.basic_feature.data.remote.api.PokemonApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PokemonModule {

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

}