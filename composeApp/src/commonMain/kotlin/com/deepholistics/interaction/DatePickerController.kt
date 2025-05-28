package com.deepholistics.interaction

// commonMain
expect object DatePickerController {
    fun showDatePicker(onDateSelected: (year: Int, month: Int, day: Int) -> Unit)
}


