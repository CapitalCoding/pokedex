package tech.capitalcoding.pokedex.basic_feature.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EmptyPokemonList(
    override val results: List<PokemonDisplayable>
) : PokemonList, Parcelable{
    companion object {
        fun empty(): EmptyPokemonList = EmptyPokemonList(
            results = emptyList()
        )
    }
}