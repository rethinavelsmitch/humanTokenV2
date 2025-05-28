@file:OptIn(InternalResourceApi::class)

package humantokenv2.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/humantokenv2.composeapp.generated.resources/"

internal val Res.font.ft_bold: FontResource by lazy {
      FontResource("font:ft_bold", setOf(
        ResourceItem(setOf(), "${MD}font/ft_bold.ttf", -1, -1),
      ))
    }

internal val Res.font.ft_medium: FontResource by lazy {
      FontResource("font:ft_medium", setOf(
        ResourceItem(setOf(), "${MD}font/ft_medium.ttf", -1, -1),
      ))
    }

internal val Res.font.ft_pil_bold: FontResource by lazy {
      FontResource("font:ft_pil_bold", setOf(
        ResourceItem(setOf(), "${MD}font/ft_pil_bold.ttf", -1, -1),
      ))
    }

internal val Res.font.ft_pil_medium: FontResource by lazy {
      FontResource("font:ft_pil_medium", setOf(
        ResourceItem(setOf(), "${MD}font/ft_pil_medium.ttf", -1, -1),
      ))
    }

internal val Res.font.ft_pil_regular: FontResource by lazy {
      FontResource("font:ft_pil_regular", setOf(
        ResourceItem(setOf(), "${MD}font/ft_pil_regular.ttf", -1, -1),
      ))
    }

internal val Res.font.ft_pil_semi_bold: FontResource by lazy {
      FontResource("font:ft_pil_semi_bold", setOf(
        ResourceItem(setOf(), "${MD}font/ft_pil_semi_bold.ttf", -1, -1),
      ))
    }

internal val Res.font.ft_regular: FontResource by lazy {
      FontResource("font:ft_regular", setOf(
        ResourceItem(setOf(), "${MD}font/ft_regular.ttf", -1, -1),
      ))
    }

internal val Res.font.ft_semi_bold: FontResource by lazy {
      FontResource("font:ft_semi_bold", setOf(
        ResourceItem(setOf(), "${MD}font/ft_semi_bold.ttf", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainFont0Resources(map: MutableMap<String, FontResource>) {
  map.put("ft_bold", Res.font.ft_bold)
  map.put("ft_medium", Res.font.ft_medium)
  map.put("ft_pil_bold", Res.font.ft_pil_bold)
  map.put("ft_pil_medium", Res.font.ft_pil_medium)
  map.put("ft_pil_regular", Res.font.ft_pil_regular)
  map.put("ft_pil_semi_bold", Res.font.ft_pil_semi_bold)
  map.put("ft_regular", Res.font.ft_regular)
  map.put("ft_semi_bold", Res.font.ft_semi_bold)
}
