package tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.details

sealed class DetailIntent {
    data class ClickOnType(val type: String) : DetailIntent()
}