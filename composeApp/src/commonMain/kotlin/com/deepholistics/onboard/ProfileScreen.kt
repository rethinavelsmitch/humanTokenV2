
package com.deepholistics.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens.spacingLg
import com.deepholistics.res.AppDimens.spacingMd
import com.deepholistics.res.AppDimens.textSizeXl


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {

}

//
//@Composable
//private fun ProfileSection(
//    title: String,
//    subtitle: String,
//    titleColor: Color = AppColors.TextPrimary,
//    content: @Composable ColumnScope.() -> Unit
//) {
//    Column {
//        Text(
//            text = title,
//            color = titleColor,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 4.dp)
//        )
//
//        Text(
//            text = subtitle,
//            color = AppColors.textSecondary,
//            fontSize = 14.sp,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            colors = CardDefaults.cardColors(
//                containerColor = Color(0xFF2A2A2A)
//            ),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                content()
//            }
//        }
//    }
//}
//
//@Composable
//private fun UserProfileCard(
//    name: String,
//    email: String
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box(
//            modifier = Modifier
//                .size(48.dp)
//                .clip(CircleShape)
//                .background(Color(0xFF8B5CF6)),
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                imageVector = Icons.Default.Person,
//                contentDescription = "Profile Picture",
//                tint = Color.White,
//                modifier = Modifier.size(24.dp)
//            )
//        }
//
//        Spacer(modifier = Modifier.width(16.dp))
//
//        Column {
//            Text(
//                text = name,
//                color = AppColors.TextPrimary,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium
//            )
//            Text(
//                text = email,
//                color = AppColors.textSecondary,
//                fontSize = 14.sp
//            )
//        }
//    }
//}
//
//@Composable
//private fun SubscriptionCard(
//    planName: String,
//    nextBillingDate: String,
//    isActive: Boolean
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 16.dp)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = planName,
//                color = AppColors.TextPrimary,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium
//            )
//
//            if (isActive) {
//                Surface(
//                    color = Color(0xFF8B5CF6),
//                    shape = RoundedCornerShape(12.dp)
//                ) {
//                    Text(
//                        text = "Active",
//                        color = Color.White,
//                        fontSize = 12.sp,
//                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Text(
//            text = "Next billing date: $nextBillingDate",
//            color = AppColors.textSecondary,
//            fontSize = 14.sp
//        )
//    }
//}
//
//@Composable
//private fun ProfileMenuItem(
//    icon: ImageVector,
//    title: String,
//    textColor: Color = AppColors.TextPrimary,
//    onClick: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() }
//            .padding(vertical = 12.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            imageVector = icon,
//            contentDescription = title,
//            tint = textColor,
//            modifier = Modifier.size(20.dp)
//        )
//
//        Spacer(modifier = Modifier.width(16.dp))
//
//        Text(
//            text = title,
//            color = textColor,
//            fontSize = 16.sp,
//            modifier = Modifier.weight(1f)
//        )
//
//        Icon(
//            imageVector = Icons.Default.ChevronRight,
//            contentDescription = "Navigate",
//            tint = AppColors.textSecondary,
//            modifier = Modifier.size(16.dp)
//        )
//    }
//}
