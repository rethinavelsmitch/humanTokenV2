package com.deepholistics.res

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import humantokenv2.composeapp.generated.resources.Res
import humantokenv2.composeapp.generated.resources.ft_bold
import humantokenv2.composeapp.generated.resources.ft_medium
import humantokenv2.composeapp.generated.resources.ft_pil_bold
import humantokenv2.composeapp.generated.resources.ft_pil_medium
import humantokenv2.composeapp.generated.resources.ft_pil_regular
import humantokenv2.composeapp.generated.resources.ft_pil_semi_bold
import humantokenv2.composeapp.generated.resources.ft_regular
import humantokenv2.composeapp.generated.resources.ft_semi_bold
import org.jetbrains.compose.resources.Font

object AppFonts {
    @Composable
    fun regular(): FontFamily = FontFamily(Font(Res.font.ft_regular))

    @Composable
    fun medium(): FontFamily = FontFamily(Font(Res.font.ft_medium))

    @Composable
    fun semiBold(): FontFamily = FontFamily(Font(Res.font.ft_semi_bold))

    @Composable
    fun bold(): FontFamily = FontFamily(Font(Res.font.ft_bold))


    @Composable
    fun pilRegular(): FontFamily = FontFamily(Font(Res.font.ft_pil_regular))

    @Composable
    fun pilMedium(): FontFamily = FontFamily(Font(Res.font.ft_pil_medium))

    @Composable
    fun pilSemiBold(): FontFamily = FontFamily(Font(Res.font.ft_pil_semi_bold))

    @Composable
    fun pilBold(): FontFamily = FontFamily(Font(Res.font.ft_pil_bold))
}
