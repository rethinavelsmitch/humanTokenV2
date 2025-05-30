
package com.deepholistics.onboard.screens.marketplace

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens

data class Product(
    val id: Int,
    val name: String,
    val store: String,
    val price: String,
    val originalPrice: String? = null,
    val rating: Float,
    val reviewCount: Int,
    val score: Int,
    val tags: List<String>,
    val backgroundColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketPlaceScreen() {
    var searchText by remember { mutableStateOf("") }
    var showFilterBottomSheet by remember { mutableStateOf(false) }
    
    val products = remember {
        listOf(
            Product(1, "Clay Plant Pot", "Shopify Store", "₹9.99", "₹15.99", 0f, 0, 0, listOf("Garden", "Kitchen", "Sale"), Color(0xFFE8B5A3)),
            Product(2, "Copper Light", "Shopify Store", "₹59.99", null, 0f, 0, 0, listOf("Home Decor", "Bedding", "Vintage", "Budget-friendly"), Color(0xFFD4A574)),
            Product(3, "Cream Sofa", "Shopify Store", "₹500.00", null, 0f, 0, 0, listOf("Furniture", "Seasonal", "Modern"), Color(0xFF9B8B7A)),
            Product(4, "Antique Drawers", "Shopify Store", "₹250.00", null, 0f, 0, 0, listOf("Furniture", "Vintage"), Color(0xFFE8C4A0)),
            Product(5, "Pink Armchair", "Shopify Store", "₹750.00", null, 0f, 0, 0, listOf("Furniture", "Modern"), Color(0xFFB8E6B8)),
            Product(6, "Wooden Outdoor Table", "Shopify Store", "₹99.99", null, 0f, 0, 0, listOf("Furniture", "Outdoor"), Color(0xFF8FA68E))
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppDimens.spacingMd)
    ) {
        // Search and Filter Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppDimens.spacingMd),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { 
                    Text(
                        "Search products...", 
                        color = AppColors.TextGrey,
                        fontSize = 14.sp
                    ) 
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = AppColors.TextGrey
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF8B5CF6),
                    unfocusedBorderColor = Color(0xFFE5E7EB),
                    focusedTextColor = AppColors.TextPrimary,
                    unfocusedTextColor = AppColors.TextPrimary
                ),
                singleLine = true
            )
            
            // Filter Button
            IconButton(
                onClick = { showFilterBottomSheet = true },
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color(0xFF8B5CF6),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter",
                    tint = Color.White
                )
            }
        }
        
        // Products Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }
    }
    
    // Filter Bottom Sheet
    if (showFilterBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFilterBottomSheet = false },
            containerColor = Color.White,
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(40.dp)
                        .height(4.dp)
                        .background(
                            Color(0xFFE5E7EB),
                            RoundedCornerShape(2.dp)
                        )
                )
            }
        ) {
            FilterBottomSheetContent(
                onDismiss = { showFilterBottomSheet = false }
            )
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle product click */ },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Product Image Box with Score
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        color = product.backgroundColor,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                // Score Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            Color.Black.copy(alpha = 0.7f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${product.score} Score",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Product Name
            Text(
                text = product.name,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            // Store Name
            Text(
                text = product.store,
                color = Color(0xFF9CA3AF),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
            
            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFF6B7280),
                        modifier = Modifier.size(12.dp)
                    )
                }
                Text(
                    text = " (${product.reviewCount})",
                    color = Color(0xFF9CA3AF),
                    fontSize = 10.sp
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Price and Add Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = product.price,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        product.originalPrice?.let { originalPrice ->
                            Text(
                                text = " $originalPrice",
                                color = Color(0xFF9CA3AF),
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }
                
                // Add Button
                IconButton(
                    onClick = { /* Handle add to cart */ },
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            Color(0xFFE91E63),
                            CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to cart",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            // Tags
            if (product.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    product.tags.take(3).forEach { tag ->
                        Box(
                            modifier = Modifier
                                .background(
                                    Color(0xFF374151),
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = tag,
                                color = Color(0xFF9CA3AF),
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterBottomSheetContent(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Filter Products",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Categories
        Text(
            text = "Categories",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        val categories = listOf("Garden", "Kitchen", "Home Decor", "Bedding", "Furniture", "Outdoor")
        categories.forEach { category ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle category selection */ }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = { /* Handle checkbox */ },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF8B5CF6)
                    )
                )
                Text(
                    text = category,
                    color = AppColors.TextPrimary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Price Range
        Text(
            text = "Price Range",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        val priceRanges = listOf("Under ₹50", "₹50 - ₹100", "₹100 - ₹500", "Above ₹500")
        priceRanges.forEach { range ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle price range selection */ }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = false,
                    onClick = { /* Handle radio button */ },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF8B5CF6)
                    )
                )
                Text(
                    text = range,
                    color = AppColors.TextPrimary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { /* Clear filters */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF8B5CF6)
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = null,
                    width = 1.dp
                )
            ) {
                Text("Clear")
            }
            
            Button(
                onClick = onDismiss,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B5CF6)
                )
            ) {
                Text("Apply", color = Color.White)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}
