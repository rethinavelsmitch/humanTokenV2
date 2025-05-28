package com.deepholistics.onboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.deepholistics.interaction.DatePickerController
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppColors.PurpleCardBackground
import com.deepholistics.res.AppFonts
import com.deepholistics.res.Dimens
import com.deepholistics.res.Dimens.dp_1
import com.deepholistics.res.Dimens.dp_12
import com.deepholistics.res.TextSizes
import com.deepholistics.utils.ui.PrimaryButton
import com.deepholistics.utils.ui.SystemBarPadding
import com.deepholistics.utils.ui.onIconClick
import humantokenv2.composeapp.generated.resources.Res
import humantokenv2.composeapp.generated.resources.calendar
import humantokenv2.composeapp.generated.resources.continue_button
import humantokenv2.composeapp.generated.resources.fasting_note
import humantokenv2.composeapp.generated.resources.fasting_test
import humantokenv2.composeapp.generated.resources.ic_summary_calendar
import humantokenv2.composeapp.generated.resources.schedule_blood_test_description
import humantokenv2.composeapp.generated.resources.schedule_blood_test_title
import humantokenv2.composeapp.generated.resources.test_date
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ScheduleBloodTestScreen(onClick: () -> Unit) {
    var selectedSlot by remember { mutableStateOf<String?>(null) }

    val timeSlots = listOf(
        "06:00 AM", "06:30 AM",
        "08:00 AM", "08:30 AM",
        "09:00 AM", "09:30 AM",
        "10:00 AM", "10:30 AM",
        "11:00 AM"
    )

    SystemBarPadding {
        ScreenBackground {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = Dimens.dp_16)
                    .padding(bottom = Dimens.dp_16)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(Dimens.dp_24)
            ) {
                Spacer(modifier = Modifier.height(Dimens.dp_56))

                Text(
                    text = stringResource(Res.string.schedule_blood_test_title),
                    fontSize = TextSizes.sp_28,
                    fontFamily = AppFonts.medium(),
                    color = AppColors.White
                )

                Text(
                    text = stringResource(Res.string.schedule_blood_test_description),
                    fontSize = TextSizes.sp_14,
                    fontFamily = AppFonts.regular(),
                    color = AppColors.TextGrey,
                    lineHeight = TextSizes.sp_20
                )


                Row(
                    modifier = Modifier.fillMaxWidth().padding(Dimens.dp_16),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = stringResource(Res.string.test_date),
                            fontSize = TextSizes.sp_20,
                            fontFamily = AppFonts.medium(),
                            color = AppColors.White
                        )
                        Text(
                            text = stringResource(Res.string.fasting_test),
                            fontSize = TextSizes.sp_14,
                            fontFamily = AppFonts.regular(),
                            color = AppColors.TextGrey
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        modifier = Modifier.size(Dimens.dp_32).background(
                            color = AppColors.PurpleIconBackground,
                            shape = RoundedCornerShape(dp_12)
                        ).border(
                            width = dp_1,
                            color = AppColors.BorderLineColor,
                            shape = RoundedCornerShape(dp_12)
                        ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(Dimens.dp_20).onIconClick(onClick = {
                                DatePickerController.showDatePicker { year, month, day ->
                                    println("Selected Date: $year-${month + 1}-$day")
                                }
                            }),
                            painter = painterResource(Res.drawable.ic_summary_calendar), // Using available icon
                            contentDescription = stringResource(Res.string.calendar),
                            tint = AppColors.White,
                        )
                    }
                }

                // Time Slots Grid
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val paddedTimeSlots = if (timeSlots.size % 2 != 0) {
                        timeSlots + ""
                    } else {
                        timeSlots
                    }

                    paddedTimeSlots.chunked(2).forEach { rowSlots ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            rowSlots.forEach { slot ->
                                if (slot.isNotEmpty()) {
                                    TimeSlotCard(
                                        time = slot,
                                        isSelected = selectedSlot == slot,
                                        onClick = { selectedSlot = slot },
                                        modifier = Modifier.weight(1f)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }

                Text(
                    text = stringResource(Res.string.fasting_note),
                    fontSize = TextSizes.sp_14,
                    fontFamily = AppFonts.regular(),
                    color = AppColors.TextGrey,
                    lineHeight = TextSizes.sp_20
                )

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PrimaryButton(
                        onClick = onClick,
                        buttonName = stringResource(Res.string.continue_button),
                        isEnable = selectedSlot != null
                    )
                }
            }
        }
    }
}

@Composable
private fun TimeSlotCard(
    time: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (isSelected) AppColors.White else PurpleCardBackground
    val textColor = if (isSelected) AppColors.Black else AppColors.White
    val borderColor = if (isSelected) AppColors.White else AppColors.BorderLineColor

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(dp_12))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(dp_12),
        border = BorderStroke(width = dp_1, color = borderColor)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time,
                fontSize = TextSizes.sp_16,
                fontFamily = AppFonts.medium(),
                color = textColor
            )
        }
    }
}