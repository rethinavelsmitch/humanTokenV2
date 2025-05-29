
package com.deepholistics.onboard.screens.marketplace

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens.spacingLg
import com.deepholistics.res.AppDimens.spacingMd
import com.deepholistics.res.TextSizes
import org.jetbrains.compose.resources.painterResource
import humantokenv2.composeapp.generated.resources.Res

data class SearchSuggestion(
    val title: String,
    val subtitle: String,
    val icon: String,
    val type: SearchType
)

enum class SearchType {
    RECENT, NEARBY, SUGGESTED
}

private val searchSuggestions = listOf(
    SearchSuggestion("Vitamin D3", "20-23 Jun • 2 products", "home", SearchType.RECENT),
    SearchSuggestion("Nearby", "Find what's around you", "navigation", SearchType.NEARBY),
    SearchSuggestion("Protein Powder", "A short distance from your Vitamin D3 search", "home", SearchType.SUGGESTED),
    SearchSuggestion("Fish Oil", "Customers interested in Vitamin D3 also looked here", "home", SearchType.SUGGESTED)
)

@Composable
private fun SearchSuggestionItem(
    suggestion: SearchSuggestion,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Surface(
                color = when (suggestion.type) {
                    SearchType.RECENT -> Color(0xFFFFF3E0)
                    SearchType.NEARBY -> Color(0xFFE3F2FD)
                    SearchType.SUGGESTED -> Color(0xFFFCE4EC)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ht_logo_196),
                        contentDescription = null,
                        tint = when (suggestion.type) {
                            SearchType.RECENT -> Color(0xFFFF9800)
                            SearchType.NEARBY -> Color(0xFF2196F3)
                            SearchType.SUGGESTED -> Color(0xFFE91E63)
                        },
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = suggestion.title,
                    color = Color(0xFF1A1A1A),
                    fontSize = TextSizes.sp_16,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = suggestion.subtitle,
                    color = Color(0xFF666666),
                    fontSize = TextSizes.sp_14
                )
            }
        }
    }
}

@Composable
fun SearchScreen(
    onBackPressed: () -> Unit,
    onSearchProduct: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = spacingMd)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        
        // Header with back button and title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ht_logo_196),
                    contentDescription = "Back",
                    tint = Color(0xFF1A1A1A),
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(
                text = "Where?",
                color = Color(0xFF1A1A1A),
                fontSize = TextSizes.sp_24,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Search Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Text(
                    text = "Search destinations",
                    color = Color(0xFF999999),
                    fontSize = TextSizes.sp_16
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ht_logo_196),
                    contentDescription = "Search",
                    tint = Color(0xFF666666),
                    modifier = Modifier.size(20.dp)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE0E0E0),
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedTextColor = Color(0xFF1A1A1A),
                unfocusedTextColor = Color(0xFF1A1A1A),
                cursorColor = Color(0xFF8B5CF6),
                containerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Recent searches section
        Text(
            text = "Recent searches",
            color = Color(0xFF1A1A1A),
            fontSize = TextSizes.sp_18,
            fontWeight = FontWeight.SemiBold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Recent search item
        SearchSuggestionItem(
            suggestion = searchSuggestions[0],
            onClick = { onSearchProduct(searchSuggestions[0].title) }
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Suggested destinations section
        Text(
            text = "Suggested destinations",
            color = Color(0xFF1A1A1A),
            fontSize = TextSizes.sp_18,
            fontWeight = FontWeight.SemiBold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Suggested items
        searchSuggestions.drop(1).forEach { suggestion ->
            SearchSuggestionItem(
                suggestion = suggestion,
                onClick = { onSearchProduct(suggestion.title) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        
        Spacer(modifier = Modifier.height(100.dp))
        
        // Bottom action buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // When button
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "When",
                        color = Color(0xFF1A1A1A),
                        fontSize = TextSizes.sp_16,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Add dates",
                        color = Color(0xFF666666),
                        fontSize = TextSizes.sp_16
                    )
                }
            }
            
            // Who button
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Who",
                        color = Color(0xFF1A1A1A),
                        fontSize = TextSizes.sp_16,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Add guests",
                        color = Color(0xFF666666),
                        fontSize = TextSizes.sp_16
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Bottom buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Clear all",
                color = Color(0xFF1A1A1A),
                fontSize = TextSizes.sp_16,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { }
            )
            
            Surface(
                onClick = { onSearchProduct(searchQuery) },
                color = Color(0xFFE91E63),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ht_logo_196),
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Search",
                        color = Color.White,
                        fontSize = TextSizes.sp_16,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}
