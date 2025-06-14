package com.deepholistics

import platform.UIKit.UIViewController

interface NativeViewFactory {
    fun createButtonView(label: String, onClick: () -> Unit): UIViewController
    fun showDatePicker(selectedDate: String, onDismiss: () -> Unit, onDateSelected: (String) -> Unit)
    fun showAlertDialog(primaryText: String, secondaryText: String, onDismiss: () -> Unit, onLogout: () -> Unit)
}