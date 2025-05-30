/*
package com.deepholistics.onboard.screens.dashboard

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deepholistics.bioMarkerIcon
import com.deepholistics.chatAssistant
import com.deepholistics.dashboardIcon
import com.deepholistics.data.models.Biomarker
import com.deepholistics.data.models.HealthMetric
import com.deepholistics.data.models.TestResult
import com.deepholistics.onboard.viewmodel.DashboardViewModel
import com.deepholistics.recommendationIcon
import com.deepholistics.res.AppColors

@Composable
fun DashboardScreen() {


    val viewModel: DashboardViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    println("--> Launched Dashboard Screen.. ${uiState.healthOverview?.lastUpdated}")

    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1A1A1A), Color(0xFF2D2D2D)
                )
            )
        )
    ) {
        when {
            uiState.isLoading -> {
                LoadingScreen()
            }

            uiState.error != null -> {
                ErrorScreen(
                    error = uiState.error!!, onRetry = { viewModel.retry() })
            }

            uiState.healthOverview != null -> {
                HealthDashboardContent(
                    healthOverview = uiState.healthOverview!!,
                    onRefresh = { viewModel.loadHealthOverview() })
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator(
                color = Color(0xFF8B5CF6), strokeWidth = 3.dp, modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Loading your health data...",
                color = AppColors.TextSecondary,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun ErrorScreen(
    error: String, onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = bioMarkerIcon,
                contentDescription = "Error",
                tint = Color(0xFFFF6B6B),
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Oops! Something went wrong",
                color = AppColors.TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = error,
                color = AppColors.TextSecondary,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onRetry, colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B5CF6)
                )
            ) {
                Text(
                    text = "Try Again", color = Color.White
                )
            }
        }
    }
}

@Composable
private fun HealthDashboardContent(
    healthOverview: com.deepholistics.data.models.HealthOverview, onRefresh: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        item {
            DashboardHeader(
                overallScore = healthOverview.overallScore,
                riskLevel = healthOverview.riskLevel,
                onRefresh = onRefresh
            )
        }

        item {
            HealthMetricsSection(healthOverview.healthMetrics)
        }

        item {
            BiomarkersSection(healthOverview.biomarkers)
        }

        item {
            RecentTestsSection(healthOverview.recentTests)
        }

        item {
            RecommendationsSection(healthOverview.recommendations)
        }

        item {
            NextStepsSection(
                nextTestDue = healthOverview.nextTestDue, lastUpdated = healthOverview.lastUpdated
            )
        }
    }
}

@Composable
private fun DashboardHeader(
    overallScore: Int, riskLevel: String, onRefresh: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ), shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Health Overview",
                    color = AppColors.TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onRefresh) {
                    Icon(
                        imageVector = recommendationIcon,
                        contentDescription = "Refresh",
                        tint = Color(0xFF8B5CF6)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Circular Progress for Overall Score
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.size(120.dp)
            ) {
                val animatedProgress by animateFloatAsState(
                    targetValue = overallScore / 100f,
                    animationSpec = tween(durationMillis = 1500),
                    label = "progress"
                )

                CircularProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier.size(120.dp),
                    color = when {
                        overallScore >= 80 -> Color(0xFF4CAF50)
                        overallScore >= 60 -> Color(0xFFFFA726)
                        else -> Color(0xFFFF6B6B)
                    },
                    strokeWidth = 8.dp,
                    trackColor = Color(0xFF3A3A3A)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$overallScore",
                        color = AppColors.TextPrimary,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Health Score", color = AppColors.TextPrimary, fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = when (riskLevel.lowercase()) {
                    "low" -> Color(0xFF4CAF50)
                    "medium" -> Color(0xFFFFA726)
                    else -> Color(0xFFFF6B6B)
                }, shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "$riskLevel Risk",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun HealthMetricsSection(healthMetrics: List<HealthMetric>) {
    Column {
        Text(
            text = "Health Metrics",
            color = AppColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(healthMetrics) { metric ->
                HealthMetricCard(metric)
            }
        }
    }
}

@Composable
private fun HealthMetricCard(metric: HealthMetric) {
    Card(
        modifier = Modifier.width(140.dp), colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ), shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = getIconForMetric(metric.icon),
                contentDescription = metric.name,
                tint = Color(0xFF8B5CF6),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = metric.name,
                color = AppColors.TextSecondary,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = metric.value,
                color = AppColors.TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Progress bar
            val animatedProgress by animateFloatAsState(
                targetValue = metric.progress,
                animationSpec = tween(durationMillis = 1000),
                label = "metric_progress"
            )

            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                color = if (metric.isPositive) Color(0xFF4CAF50) else Color(0xFFFF6B6B),
                trackColor = Color(0xFF3A3A3A)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = metric.change,
                color = if (metric.isPositive) Color(0xFF4CAF50) else Color(0xFFFF6B6B),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun BiomarkersSection(biomarkers: List<Biomarker>) {
    Column {
        Text(
            text = "Key Biomarkers",
            color = AppColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        biomarkers.forEach { biomarker ->
            BiomarkerCard(biomarker)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun BiomarkerCard(biomarker: Biomarker) {
    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ), shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = biomarker.name,
                    color = AppColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Surface(
                    color = when (biomarker.status.lowercase()) {
                        "normal" -> Color(0xFF4CAF50)
                        "low", "high" -> Color(0xFFFFA726)
                        else -> Color(0xFFFF6B6B)
                    }, shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = biomarker.status,
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${biomarker.value} ${biomarker.unit}",
                    color = AppColors.TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${if (biomarker.percentChange > 0) "+" else ""}${biomarker.percentChange}%",
                    color = if (biomarker.percentChange < 0) Color(0xFF4CAF50) else Color(0xFFFF6B6B),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Normal: ${biomarker.normalRange}",
                color = AppColors.TextSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun RecentTestsSection(recentTests: List<TestResult>) {
    Column {
        Text(
            text = "Recent Tests",
            color = AppColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        recentTests.forEach { test ->
            TestResultCard(test)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun TestResultCard(test: TestResult) {
    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ), shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = test.testName,
                    color = AppColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = test.date, color = AppColors.TextSecondary, fontSize = 14.sp
                )
                Text(
                    text = test.category, color = Color(0xFF8B5CF6), fontSize = 12.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${test.score}/100",
                    color = AppColors.TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    color = Color(0xFF4CAF50), shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = test.status,
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RecommendationsSection(recommendations: List<String>) {
    Column {
        Text(
            text = "Recommendations",
            color = AppColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2A2A2A)
            ), shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                recommendations.forEachIndexed { index, recommendation ->
                    Row(
                        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier.size(8.dp).clip(CircleShape)
                                .background(Color(0xFF8B5CF6)).padding(top = 6.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = recommendation,
                            color = AppColors.TextPrimary,
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (index < recommendations.lastIndex) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun NextStepsSection(
    nextTestDue: String, lastUpdated: String,
) {
    Column {
        Text(
            text = "Next Steps",
            color = AppColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8B5CF6).copy(alpha = 0.1f)
            ), shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = dashboardIcon,
                        contentDescription = "Schedule",
                        tint = Color(0xFF8B5CF6),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Next test due: $nextTestDue",
                        color = AppColors.TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = chatAssistant,
                        contentDescription = "Update",
                        tint = AppColors.TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Last updated: $lastUpdated",
                        color = AppColors.TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun getIconForMetric(iconName: String): ImageVector {
    return when (iconName.lowercase()) {
        "heart" -> chatAssistant
        "sleep" -> chatAssistant
        "stress" -> chatAssistant
        "activity" -> chatAssistant
        else -> chatAssistant
    }
}
*/
