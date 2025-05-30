package com.deepholistics.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens.textSizeMd
import com.deepholistics.res.AppDimens.textSizeXl
import com.deepholistics.widgets.MyDataPickerDialog
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
actual fun CommonButton(modifier: Modifier, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = modifier) {
        Text("Android Button")
    }
}

@Composable
actual fun ShowDatePicker(
    modifier: Modifier,
    selectedDate: String,
    onDismiss: () -> Unit,
    onDateSelected: (String) -> Unit,
) {
    MyDataPickerDialog(onDateSelected = { milliSec ->
        val date = Instant.ofEpochMilli(milliSec).atZone(ZoneId.systemDefault())
            .toLocalDateTime()
        val newDate = DateTimeFormatter.ofPattern("dd/mm/yyyy").format(date)
        onDateSelected(newDate)
    }, onDismiss = {
        onDismiss()
    }, selectedDate = LocalDateTime.now())
}

@Composable
actual fun ShowAlertDialog(
    modifier: Modifier,
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onLogout: () -> Unit,
) {
    AlertDialog(
        shape = RoundedCornerShape(46f),
        containerColor = AppColors.PurpleCardBackground,
        title = {
            Text(
                text = title,
                color = AppColors.TextPrimary,
                fontSize = textSizeXl,
                fontWeight = FontWeight.Medium
            )
        },
        text = {
            Text(
                text = message,
                color = AppColors.TextPrimary,
                fontSize = textSizeMd,
                fontWeight = FontWeight.Normal
            )
        },
        onDismissRequest = {
        },
        confirmButton = {
            Text(
                text = "OK",
                color = AppColors.TextPrimary,
                fontSize = textSizeMd,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .clip(RoundedCornerShape(36f))
                    .background(
                        color = AppColors.Transparent,
                        shape = RoundedCornerShape(36f)
                    )
                    .onTextClick(
                        rippleEffect = true,
                        onClick = {
                            onLogout()
                        }
                    )
            )
        },
        dismissButton = {
            Text(
                text = "Cancel",
                color = AppColors.TextPrimary,
                fontSize = textSizeMd,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .clip(RoundedCornerShape(36f))
                    .background(
                        color = AppColors.Transparent,
                        shape = RoundedCornerShape(36f)
                    )
                    .onTextClick(
                        rippleEffect = true,
                        onClick = {
                            onDismiss()
                        }
                    )
            )
        }
    )
}

