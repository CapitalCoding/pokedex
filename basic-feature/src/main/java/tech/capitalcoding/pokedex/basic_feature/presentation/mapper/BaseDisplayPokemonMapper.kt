package tech.capitalcoding.pokedex.basic_feature.presentation.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.Pokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonListResult
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonType
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonTypeListResult
import tech.capitalcoding.pokedex.basic_feature.domain.model.SearchTypeListResult
import tech.capitalcoding.pokedex.basic_feature.presentation.model.DisplayablePokemonDetails
import tech.capitalcoding.pokedex.basic_feature.presentation.model.DisplayableType
import tech.capitalcoding.pokedex.basic_feature.presentation.model.PagedPokemonList
import tech.capitalcoding.pokedex.basic_feature.presentation.model.PokemonDisplayable
import tech.capitalcoding.pokedex.basic_feature.presentation.model.SearchPokemonList


fun BasePokemon.toDisplayModel(): PokemonDisplayable {
    return PokemonDisplayable(
        id = this.id,
        name = this.name.capitalize(Locale.current),
        imageUrl = this.spriteUrl
    )
}

fun PokemonListResult<BasePokemon>.toDisplayModel(): PagedPokemonList {
    return PagedPokemonList(
        count = this.count,
        next = this.next,
        previous = this.previous,
        results = this.items.map { it.toDisplayModel() },
        nextOffset = this.nextOffset,
        previousOffset = this.previousOffset
    )
}

fun PokemonType.toDisplayableModel(): DisplayableType {
    return DisplayableType(
        name = this.name.capitalize(Locale.current),
        url = this.url
    )
}

fun SearchTypeListResult<PokemonType>.toDisplayableModel(): SearchTypeListResult<DisplayableType> {
    return SearchTypeListResult(
        count = this.count,
        next = this.next,
        previous = this.previous,
        items = this.items.map { it.toDisplayableModel() },
        nextOffset = this.nextOffset,
        previousOffset = this.previousOffset
    )
}

fun PokemonTypeListResult<BasePokemon>.toDisplayableModel(): SearchPokemonList {
    return SearchPokemonList(
        id = this.id,
        name = this.name,
        results = this.results.map { it.toDisplayModel() }
    )
}

fun Pokemon.toDisplayableModel(): DisplayablePokemonDetails {
    return DisplayablePokemonDetails(
        id = this.id.toInt(),
        name = this.name.capitalize(Locale.current),
        height = this.height,
        weight = this.weight,
        spriteNormalFront = this.sprites.frontDefault,
        spriteShinyFront = this.sprites.frontShiny,
        types = this.types.map { it.toDisplayableModel() },
        speed = this.stats.find { it.name == "speed" }?.value ?: 0,
        defence = this.stats.find { it.name == "defense" }?.value ?: 0,
        attack = this.stats.find { it.name == "attack" }?.value ?: 0,
    )
}
