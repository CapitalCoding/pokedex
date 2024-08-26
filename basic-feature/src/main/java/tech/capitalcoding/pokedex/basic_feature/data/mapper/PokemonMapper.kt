package tech.capitalcoding.pokedex.basic_feature.data.mapper

import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonDetailResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonsListItemResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.StatsResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.TypeResponse
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.Pokemon
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
        stats = this.stats.map { it.toDomainModel() }
    )
}


fun PokemonsListItemResponse.toDomainModel(id: String): BasePokemon {
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
