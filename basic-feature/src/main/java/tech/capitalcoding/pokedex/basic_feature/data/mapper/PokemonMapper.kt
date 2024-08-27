package tech.capitalcoding.pokedex.basic_feature.data.mapper

import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonDetailResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.type.PokemonTypeResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonsListItemResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.SpritesResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.StatsResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.type.TypeResponse
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.Pokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonSprites
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonStat
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonType

private const val POKEMON_IMAGE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

fun PokemonDetailResponse.toDomainModel(): Pokemon {
    return Pokemon(
        id = this.id.toString(),
        name = this.name,
        imageUrl = this.sprites.frontDefault,
        height = this.height,
        weight = this.weight,
        types = this.types.map { it.toDomainModel() },
        abilities = this.abilities.map { it.ability.name },
        stats = this.stats.map { it.toDomainModel() },
        sprites = this.sprites.toDomainModel()
    )
}

private fun SpritesResponse.toDomainModel(): PokemonSprites {
    return PokemonSprites(
        frontDefault = this.frontDefault,
        backDefault = this.backDefault,
        frontShiny = this.frontShiny,
        backShiny = this.backShiny
    )
}


fun PokemonsListItemResponse.toDomainModel(): BasePokemon {
    val id = extractPokemonIdFromUrl(this.url)
    return BasePokemon(
        id = id,
        name = this.name,
        url = this.url,
        spriteUrl = "$POKEMON_IMAGE${id}.png"
    )
}

fun StatsResponse.toDomainModel(): PokemonStat {
    return PokemonStat(
        name = this.stat.name,
        value = this.baseStat
    )
}

fun TypeResponse.toDomainModel(): PokemonType {
    return PokemonType(
        name = this.item.name,
        url = this.item.url,
        slot = this.slot
    )
}

fun extractPokemonIdFromUrl(url: String): String {
    return url.trimEnd('/').substringAfterLast('/')
}


fun PokemonTypeResponse.toDomainModel(): PokemonType {
    return PokemonType(
        name = this.name,
        url = this.url,
        slot = 0
    )
}
