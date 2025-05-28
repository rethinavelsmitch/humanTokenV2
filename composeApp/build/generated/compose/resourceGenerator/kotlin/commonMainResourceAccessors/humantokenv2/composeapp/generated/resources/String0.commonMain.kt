@file:OptIn(InternalResourceApi::class)

package humantokenv2.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.StringResource

private const val MD: String = "composeResources/humantokenv2.composeapp.generated.resources/"

internal val Res.string.access_dashboard: StringResource by lazy {
      StringResource("string:access_dashboard", "access_dashboard", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 10, 52),
      ))
    }

internal val Res.string.app_name: StringResource by lazy {
      StringResource("string:app_name", "app_name", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 63, 36),
      ))
    }

internal val Res.string.at_home_blood_draw: StringResource by lazy {
      StringResource("string:at_home_blood_draw", "at_home_blood_draw", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 100, 50),
      ))
    }

internal val Res.string.back: StringResource by lazy {
      StringResource("string:back", "back", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 151, 20),
      ))
    }

internal val Res.string.blood_draw_desc: StringResource by lazy {
      StringResource("string:blood_draw_desc", "blood_draw_desc", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 172, 147),
      ))
    }

internal val Res.string.continue_to_pay: StringResource by lazy {
      StringResource("string:continue_to_pay", "continue_to_pay", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 320, 59),
      ))
    }

internal val Res.string.dashboard_desc: StringResource by lazy {
      StringResource("string:dashboard_desc", "dashboard_desc", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 380, 186),
      ))
    }

internal val Res.string.next_steps: StringResource by lazy {
      StringResource("string:next_steps", "next_steps", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 567, 34),
      ))
    }

internal val Res.string.payment_confirmation: StringResource by lazy {
      StringResource("string:payment_confirmation", "payment_confirmation", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 732, 56),
      ))
    }

internal val Res.string.payment_confirmation_desc: StringResource by lazy {
      StringResource("string:payment_confirmation_desc", "payment_confirmation_desc", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 602, 129),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("access_dashboard", Res.string.access_dashboard)
  map.put("app_name", Res.string.app_name)
  map.put("at_home_blood_draw", Res.string.at_home_blood_draw)
  map.put("back", Res.string.back)
  map.put("blood_draw_desc", Res.string.blood_draw_desc)
  map.put("continue_to_pay", Res.string.continue_to_pay)
  map.put("dashboard_desc", Res.string.dashboard_desc)
  map.put("next_steps", Res.string.next_steps)
  map.put("payment_confirmation", Res.string.payment_confirmation)
  map.put("payment_confirmation_desc", Res.string.payment_confirmation_desc)
}
