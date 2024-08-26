package tech.capitalcoding.pokedex.core.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimaryColor,
    primaryContainer = PrimaryLightColor,
    onPrimaryContainer = PrimaryDarkColor,
    secondary = SecondaryColor,
    onSecondary = OnSecondaryColor,
    secondaryContainer = SecondaryLightColor,
    onSecondaryContainer = SecondaryDarkColor,
    background = BackgroundColor,
    onBackground = OnBackgroundColor,
    surface = SurfaceColor,
    onSurface = OnSurfaceColor,
    error = ErrorColor,
    onError = OnErrorColor
)

private val DarkColorPalette = darkColorScheme(
    primary = PrimaryDarkColor,
    onPrimary = OnPrimaryColor,
    primaryContainer = PrimaryColor,
    onPrimaryContainer = PrimaryLightColor,
    secondary = SecondaryDarkColor,
    onSecondary = OnSecondaryColor,
    secondaryContainer = SecondaryColor,
    onSecondaryContainer = SecondaryLightColor,
    background = Color.Black,
    onBackground = OnBackgroundColor,
    surface = Color.Black,
    onSurface = OnSurfaceColor,
    error = ErrorColor,
    onError = OnErrorColor
)

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = PokemonTypography,
        content = content
    )
}