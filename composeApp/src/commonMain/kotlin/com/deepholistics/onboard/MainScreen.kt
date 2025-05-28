package com.deepholistics.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepholistics.Greeting
import com.deepholistics.onboard.viewmodel.AuthViewModel


@Composable
fun MainScreen(
    authViewModel: AuthViewModel, onLogout: () -> Unit
) {
    val authState by authViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome, ${authState.user?.fullName ?: "User"}!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Email: ${authState.user?.email ?: ""}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = Greeting().greet(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                authViewModel.logout()
                onLogout()
            }) {
            Text("Logout")
        }
    }
}


package com.deepholistics.onboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Recommend
import androidx.compose.material.icons.filled.ShoppingCart
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
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.onboard.screens.dashboard.DashboardScreen
import com.deepholistics.onboard.screens.biomarkers.BioMarkersScreen
import com.deepholistics.onboard.screens.recommendations.RecommendationsScreen
import com.deepholistics.onboard.screens.marketplace.MarketPlaceScreen
import com.deepholistics.onboard.screens.chat.ChatAssistantScreen
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens.textSizeXl
import humantokenv2.composeapp.generated.resources.Res
import humantokenv2.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateToProfile: () -> Unit) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            title = "Dashboard",
            icon = Icons.Default.Dashboard,
            screen = { DashboardScreen() }
        ),
        BottomNavigationItem(
            title = "BioMarkers",
            icon = Icons.Default.Analytics,
            screen = { BioMarkersScreen() }
        ),
        BottomNavigationItem(
            title = "Recommendations",
            icon = Icons.Default.Recommend,
            screen = { RecommendationsScreen() }
        ),
        BottomNavigationItem(
            title = "MarketPlace",
            icon = Icons.Default.ShoppingCart,
            screen = { MarketPlaceScreen() }
        ),
        BottomNavigationItem(
            title = "Chat Assistant",
            icon = Icons.Default.Chat,
            screen = { ChatAssistantScreen() }
        )
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
                    },
                    actions = {
                        IconButton(onClick = onNavigateToProfile) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Profile",
                                tint = AppColors.TextPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = AppColors.SurfaceDark
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = AppColors.SurfaceDark
                ) {
                    bottomNavigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontSize = AppColors.TextSecondary
                                )
                            },
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = AppColors.Primary,
                                selectedTextColor = AppColors.Primary,
                                unselectedIconColor = AppColors.TextSecondary,
                                unselectedTextColor = AppColors.TextSecondary,
                                indicatorColor = AppColors.Primary.copy(alpha = 0.2f)
                            )
                        )
                    }
                }
            },
            containerColor = AppColors.Transparent
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                bottomNavigationItems[selectedTabIndex].screen()
            }
        }
    }
}
