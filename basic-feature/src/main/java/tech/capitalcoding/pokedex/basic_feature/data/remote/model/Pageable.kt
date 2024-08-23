package tech.capitalcoding.pokedex.basic_feature.data.remote.model

interface Pageable<T> {
    val count: Int
    val next: String?
    val previous: String?
    val results: List<T>
}