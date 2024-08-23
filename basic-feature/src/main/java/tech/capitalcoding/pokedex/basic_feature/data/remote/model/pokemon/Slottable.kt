package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

interface Slottable<T> {
    val item: T
    val slot: Int
}