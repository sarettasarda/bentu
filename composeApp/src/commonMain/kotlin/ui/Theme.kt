package ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = AppColors.Primary,
    onPrimary = AppColors.OnPrimary,
    secondary = AppColors.Secondary,
    background = AppColors.Background,
    surface = AppColors.Surface,
    onBackground = AppColors.OnBackground,
    onSurface = AppColors.OnSurface,
    error = AppColors.Error
)

private val DarkColors = darkColorScheme(
    primary = AppColors.Secondary,
    onPrimary = AppColors.OnPrimary,
    secondary = AppColors.Primary,
    background = AppColors.OnBackground,
    surface = AppColors.OnSurface,
    onBackground = AppColors.Background,
    onSurface = AppColors.Surface,
    error = AppColors.Error
)

private val AppShapes = Shapes(
    small = RoundedCornerShape(Dimens.cardCornerSmall),
    medium = RoundedCornerShape(Dimens.cardCornerMedium),
    large = RoundedCornerShape(Dimens.cardCornerLarge)
)

private val AppTypography = Typography() // customize if needed

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}