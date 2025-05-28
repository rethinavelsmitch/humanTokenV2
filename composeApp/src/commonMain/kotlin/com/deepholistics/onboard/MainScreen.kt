package com.deepholistics.onboard


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.deepholistics.dashboardIcon
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.onboard.screens.dashboard.DashboardScreen
import com.deepholistics.onboard.screens.biomarkers.BioMarkersScreen
import com.deepholistics.onboard.screens.recommendations.RecommendationsScreen
import com.deepholistics.onboard.screens.marketplace.MarketPlaceScreen
import com.deepholistics.onboard.screens.chat.ChatAssistantScreen
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens
import com.deepholistics.res.AppDimens.textSizeXl
import humantokenv2.composeapp.generated.resources.Res
import humantokenv2.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

data class BottomNavigationItem(
    val title: String, val icon: @Composable () -> Unit, val screen: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateToProfile: () -> Unit) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            title = "Dashboard",
            icon = { Icon(imageVector = dashboardIcon, contentDescription = "Dashboard") },
            screen = { DashboardScreen() }), BottomNavigationItem(
            title = "BioMarkers",
            icon = { Icon(imageVector = dashboardIcon, contentDescription = "BioMarkers") },
            screen = { BioMarkersScreen() }), BottomNavigationItem(
            title = "Recommendations",
            icon = { Icon(imageVector = dashboardIcon, contentDescription = "Recommendations") },
            screen = { RecommendationsScreen() }), BottomNavigationItem(
            title = "MarketPlace",
            icon = { Icon(imageVector = dashboardIcon, contentDescription = "MarketPlace") },
            screen = { MarketPlaceScreen() }), BottomNavigationItem(
            title = "Chat Assistant",
            icon = { Icon(imageVector = dashboardIcon, contentDescription = "Chat Assistant") },
            screen = { ChatAssistantScreen() })
    )

    ScreenBackground {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.app_name),
                            color = AppColors.TextPrimary,
                            fontSize = textSizeXl,
                            fontWeight = FontWeight.Bold
                        )
                    }, actions = {
                        IconButton(onClick = onNavigateToProfile) {
                            Icon(
                                imageVector = dashboardIcon,
                                contentDescription = "Chat Assistant",
                                tint = AppColors.TextPrimary
                            )
                        }
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = AppColors.SurfaceDark
                    )
                )
            }, bottomBar = {
                NavigationBar(
                    containerColor = AppColors.SurfaceDark
                ) {
                    bottomNavigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = item.icon,
                            label = {
                                Text(
                                    text = item.title, fontSize = AppDimens.textSizeMedium
                                )
                            },
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = AppColors.Primary,
                                selectedTextColor = AppColors.Primary,
                                unselectedIconColor = AppColors.TextGrey,
                                unselectedTextColor = AppColors.TextGrey,
                                indicatorColor = AppColors.Primary.copy(alpha = 0.2f)
                            )
                        )
                    }
                }
            }, containerColor = AppColors.Transparent
        ) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                bottomNavigationItems[selectedTabIndex].screen()
            }
        }
    }
}
