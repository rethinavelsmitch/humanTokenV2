package com.deepholistics.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.deepholistics.R
import com.deepholistics.ui.onTextClick
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDataPickerDialog(
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
    selectedDate: LocalDateTime,
) {
    val year = selectedDate.year
    val month = selectedDate.month
    val day = selectedDate.dayOfMonth
    val displayDate = LocalDateTime.of(year, month, day, 21, 1, 0)

    val selectedDay =
        displayDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDay,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        })

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Text(
                text = stringResource(id = R.string.ok),
                color = colorResource(id = R.color.colorPrimaryDark),
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen._24dp))
                    .padding(bottom = dimensionResource(id = R.dimen._12dp))
                    .onTextClick(onClick = {
                        onDateSelected(datePickerState.selectedDateMillis ?: 0)
                        onDismiss()
                    })
            )
        },
        dismissButton = {
            Text(
                text = stringResource(id = R.string.cancel),
                color = colorResource(id = R.color.colorPrimaryDark),
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen._24dp))
                    .padding(bottom = dimensionResource(id = R.dimen._12dp))
                    .onTextClick(onClick = {
                        onDismiss()
                    })
            )
        }) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = colorResource(id = R.color.colorPrimaryDark),
                todayContentColor = colorResource(id = R.color.colorPrimaryDark),
                todayDateBorderColor = colorResource(id = R.color.colorPrimaryDark)
            )
        )
    }
}