@file:OptIn(InternalResourceApi::class)

package humantokenv2.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/humantokenv2.composeapp.generated.resources/"

internal val Res.drawable.compose_multiplatform: DrawableResource by lazy {
      DrawableResource("drawable:compose_multiplatform", setOf(
        ResourceItem(setOf(), "${MD}drawable/compose-multiplatform.xml", -1, -1),
      ))
    }

internal val Res.drawable.ht_logo_196: DrawableResource by lazy {
      DrawableResource("drawable:ht_logo_196", setOf(
        ResourceItem(setOf(), "${MD}drawable/ht_logo_196.png", -1, -1),
      ))
    }

internal val Res.drawable.ic_payment: DrawableResource by lazy {
      DrawableResource("drawable:ic_payment", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_payment.png", -1, -1),
      ))
    }

internal val Res.drawable.ic_puzzle: DrawableResource by lazy {
      DrawableResource("drawable:ic_puzzle", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_puzzle.png", -1, -1),
      ))
    }

internal val Res.drawable.ic_tag: DrawableResource by lazy {
      DrawableResource("drawable:ic_tag", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_tag.png", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("compose_multiplatform", Res.drawable.compose_multiplatform)
  map.put("ht_logo_196", Res.drawable.ht_logo_196)
  map.put("ic_payment", Res.drawable.ic_payment)
  map.put("ic_puzzle", Res.drawable.ic_puzzle)
  map.put("ic_tag", Res.drawable.ic_tag)
}
