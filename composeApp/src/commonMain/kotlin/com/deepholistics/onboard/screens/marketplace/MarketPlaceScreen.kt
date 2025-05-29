
package com.deepholistics.onboard.screens.marketplace

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens
import com.deepholistics.res.AppDimens.spacingLg
import com.deepholistics.res.AppDimens.spacingMd
import com.deepholistics.res.TextSizes

data class Product(
    val id: Int,
    val name: String,
    val store: String,
    val price: String,
    val originalPrice: String? = null,
    val rating: Float,
    val reviewCount: Int,
    val categories: List<String>,
    val healthScore: Int
)

private val healthProducts = listOf(
    Product(1, "Vitamin D3 Supplement", "HealthStore", "₹299", "₹399", 4.5f, 128, listOf("Supplements", "Vitamins"), 85),
    Product(2, "Omega-3 Fish Oil", "NutritionHub", "₹599", null, 4.8f, 94, listOf("Supplements", "Heart Health"), 92),
    Product(3, "Blood Pressure Monitor", "MedTech", "₹2499", "₹2999", 4.3f, 67, listOf("Devices", "Monitoring"), 78),
    Product(4, "Protein Powder", "FitLife", "₹1299", null, 4.6f, 203, listOf("Nutrition", "Protein"), 88),
    Product(5, "Sleep Tracker Band", "TechHealth", "₹3999", "₹4999", 4.4f, 89, listOf("Devices", "Sleep"), 81),
    Product(6, "Multivitamin Tablets", "WellnessPlus", "₹449", null, 4.7f, 156, listOf("Supplements", "Vitamins"), 90)
)

@Composable
private fun FilterChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    FilterChip(
        onClick = { },
        label = {
            Text(
                text = text,
                color = if (isSelected) Color.White else AppColors.TextPrimary,
                fontSize = TextSizes.sp_12
            )
        },
        selected = isSelected,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFF8B5CF6),
            containerColor = Color(0xFF2A2A2A)
        ),
        modifier = modifier
    )
}

@Composable
private fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Product Image Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF3A3A3A)),
                contentAlignment = Alignment.Center
            ) {
                // Health Score Badge
                Surface(
                    color = Color(0xFF8B5CF6),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${product.healthScore} Score",
                        color = Color.White,
                        fontSize = TextSizes.sp_10,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Product Image",
                    tint = AppColors.TextGrey,
                    modifier = Modifier.size(32.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Product Name
            Text(
                text = product.name,
                color = AppColors.TextPrimary,
                fontSize = TextSizes.sp_14,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )
            
            // Store Name
            Text(
                text = product.store,
                color = AppColors.TextGrey,
                fontSize = TextSizes.sp_12
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${product.rating}",
                    color = AppColors.TextPrimary,
                    fontSize = TextSizes.sp_12
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "(${product.reviewCount})",
                    color = AppColors.TextGrey,
                    fontSize = TextSizes.sp_12
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Price
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.price,
                        color = AppColors.TextPrimary,
                        fontSize = TextSizes.sp_14,
                        fontWeight = FontWeight.Bold
                    )
                    
                    product.originalPrice?.let { originalPrice ->
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = originalPrice,
                            color = AppColors.TextGrey,
                            fontSize = TextSizes.sp_12,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
                
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to Cart",
                        tint = Color(0xFF8B5CF6),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            // Categories
            Row {
                product.categories.take(2).forEach { category ->
                    Surface(
                        color = Color(0xFF3A3A3A),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Text(
                            text = category,
                            color = AppColors.TextGrey,
                            fontSize = TextSizes.sp_10,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterBottomSheet(
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var selectedHealthNeeds by remember { mutableStateOf(setOf<String>()) }
    
    val categories = listOf("Product", "Blood", "Gene", "Gut")
    val healthNeeds = listOf(
        "Amino Acids",
        "Bone & Joint", 
        "Children's Health",
        "Cognition & Focus",
        "Energy",
        "Fish Oil & Omegas",
        "Sleep Quality",
        "Gut Health",
        "Immunity"
    )
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color(0xFF1A1A1A),
        contentColor = AppColors.TextPrimary,
        dragHandle = {
            BottomSheetDefaults.DragHandle(color = AppColors.TextGrey)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacingMd)
                .padding(bottom = 32.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(spacingLg)
        ) {
            Text(
                text = "Filter Options",
                color = AppColors.TextPrimary,
                fontSize = TextSizes.sp_20,
                fontWeight = FontWeight.Bold
            )
            
            // Categories Section
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Categories",
                    color = AppColors.TextPrimary,
                    fontSize = TextSizes.sp_16,
                    fontWeight = FontWeight.Medium
                )
                
                categories.forEach { category ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedCategories.contains(category),
                                onClick = {
                                    selectedCategories = if (selectedCategories.contains(category)) {
                                        selectedCategories - category
                                    } else {
                                        selectedCategories + category
                                    }
                                }
                            )
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedCategories.contains(category),
                            onCheckedChange = null,
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF8B5CF6),
                                uncheckedColor = AppColors.TextGrey,
                                checkmarkColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = category,
                            color = AppColors.TextPrimary,
                            fontSize = TextSizes.sp_14
                        )
                    }
                }
            }
            
            // Health Needs Section
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Health Needs",
                    color = AppColors.TextPrimary,
                    fontSize = TextSizes.sp_16,
                    fontWeight = FontWeight.Medium
                )
                
                healthNeeds.forEach { healthNeed ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedHealthNeeds.contains(healthNeed),
                                onClick = {
                                    selectedHealthNeeds = if (selectedHealthNeeds.contains(healthNeed)) {
                                        selectedHealthNeeds - healthNeed
                                    } else {
                                        selectedHealthNeeds + healthNeed
                                    }
                                }
                            )
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedHealthNeeds.contains(healthNeed),
                            onCheckedChange = null,
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF8B5CF6),
                                uncheckedColor = AppColors.TextGrey,
                                checkmarkColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = healthNeed,
                            color = AppColors.TextPrimary,
                            fontSize = TextSizes.sp_14
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MarketPlaceScreen() {
    var showFilterBottomSheet by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacingMd)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(spacingLg)
    ) {
        // Top row with search and filter
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search field
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        text = "Search products...",
                        color = AppColors.TextGrey,
                        fontSize = TextSizes.sp_14
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = AppColors.TextGrey,
                        modifier = Modifier.size(20.dp)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF8B5CF6),
                    unfocusedBorderColor = Color(0xFF3A3A3A),
                    focusedTextColor = AppColors.TextPrimary,
                    unfocusedTextColor = AppColors.TextPrimary,
                    cursorColor = Color(0xFF8B5CF6)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Filter button
            IconButton(
                onClick = { 
                    showFilterBottomSheet = true 
                }
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter",
                    tint = AppColors.TextPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        
        // Products Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(spacingMd),
            verticalArrangement = Arrangement.spacedBy(spacingMd),
            modifier = Modifier.height(600.dp)
        ) {
            items(healthProducts) { product ->
                ProductCard(product = product)
            }
        }
    }
    
    // Filter Bottom Sheet
    if (showFilterBottomSheet) {
        FilterBottomSheet(
            onDismiss = { showFilterBottomSheet = false }
        )
    }
}
