package tech.capitalcoding.pokedex.basic_feature.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PokemonTypeListResult<T>(
    val id: Int,
    val name: String,
    val results: @RawValue List<T>
): Parcelable
