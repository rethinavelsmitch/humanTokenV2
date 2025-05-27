package com.deepholistics.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.dp
import humantokenv2.composeapp.generated.resources.Res
import humantokenv2.composeapp.generated.resources.ht_logo_196
import org.jetbrains.compose.ui.tooling.preview.Preview


//@Preview
//@Composable
//fun PhoneNumberInputPreview() {
//    PhoneNumberInput(
//        countryCode = "+1",
//        onCountryCodeClick = {},
//        phoneNumber = "1234567890",
//        onPhoneNumberChange = {""}
//}
//

@Composable
fun PhoneNumberInput(
    countryCode: String,
    onCountryCodeClick: () -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit
) {
    val borderColor = Color(0xFF8E8E93)
    val backgroundColor = Color(0x33FFFFFF) // Slightly transparent
    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier = Modifier.fillMaxWidth().border(1.dp, borderColor, shape)
            .background(backgroundColor, shape).padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Country Code Dropdown Trigger
            Box(modifier = Modifier.clickable { onCountryCodeClick() }.padding(end = 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = countryCode, color = Color.White
                    )
//                    Icon(
//                       // imageVector = Icon,
//
//                        contentDescription = null,
//                        tint = Color.White
//                    )
                }
            }

            // Divider
            Box(
                modifier = Modifier.height(24.dp).width(1.dp)
                    .background(Color.White.copy(alpha = 0.5f)).padding(horizontal = 4.dp)
            )

            // Phone Number TextField
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                placeholder = { Text("Enter your phone number") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                singleLine = true
            )
        }
    }
}
