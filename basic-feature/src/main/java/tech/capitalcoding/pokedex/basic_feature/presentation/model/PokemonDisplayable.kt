package tech.capitalcoding.pokedex.basic_feature.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDisplayable(
    val id: String,
    val name: String,
    val imageUrl: String,
) : Parcelable
