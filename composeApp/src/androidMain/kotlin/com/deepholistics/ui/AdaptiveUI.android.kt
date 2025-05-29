package com.deepholistics.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
actual fun AlertDialog(
    modifier: Modifier,
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    TODO("Not yet implemented")
}

