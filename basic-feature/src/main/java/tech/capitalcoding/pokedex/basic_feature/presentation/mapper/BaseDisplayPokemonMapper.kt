package tech.capitalcoding.pokedex.basic_feature.presentation.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonListResult
import tech.capitalcoding.pokedex.basic_feature.presentation.model.PokemonDisplayable


fun BasePokemon.toDisplayModel(): PokemonDisplayable {
    return PokemonDisplayable(
        id = this.id,
        name = this.name.capitalize(Locale.current),
        imageUrl = this.spriteUrl
    )
}

fun PokemonListResult<BasePokemon>.toDisplayModel(): PokemonListResult<PokemonDisplayable> {
    return PokemonListResult(
        count = this.count,
        next = this.next,
        previous = this.previous,
        items = this.items.map { it.toDisplayModel() },
        nextOffset = this.nextOffset,
        previousOffset = this.previousOffset
    )
}