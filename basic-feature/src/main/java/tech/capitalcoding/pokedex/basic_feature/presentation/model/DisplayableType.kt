package tech.capitalcoding.pokedex.basic_feature.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DisplayableType(
    val name: String,
    val url: String,
): Parcelable
