package tech.capitalcoding.pokedex.basic_feature.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DisplayablePokemonDetails(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val spriteNormalFront: String,
    val spriteShinyFront: String,
    val types: List<DisplayableType>,
    val speed: Int,
    val defence: Int,
    val attack: Int,
): Parcelable {

    companion object {
        fun empty(): DisplayablePokemonDetails {
            return DisplayablePokemonDetails(
                id = 0,
                name = "",
                height = 0,
                weight = 0,
                spriteNormalFront = "",
                spriteShinyFront = "",
                types = emptyList(),
                speed = 0,
                defence = 0,
                attack = 0,
            )
        }
    }
}
