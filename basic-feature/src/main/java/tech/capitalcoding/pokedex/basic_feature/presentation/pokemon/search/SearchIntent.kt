package tech.capitalcoding.pokedex.basic_feature.presentation.pokemon.search

sealed class SearchIntent {
    data class LoadPreviousPage(val previousOffset: Int) : SearchIntent()
    data class LoadNextPage(val nextOffset: Int) : SearchIntent()
    data class SearchByName(val name: String) : SearchIntent()
    data class SearchByType(val type: String) : SearchIntent()
    data class SearchById(val id: String) : SearchIntent()
}