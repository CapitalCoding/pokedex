package tech.capitalcoding.pokedex.basic_feature.presentation.model

data class PokemonStatDisplayable(
    val name: String,
    val value: Int,
    val colorRes: Int
) {
    val valueString: String
        get() = value.toString()
}
