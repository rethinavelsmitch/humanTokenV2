package com.deepholistics.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.deepholistics.onboard.helper.PrimaryButton
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.onboard.viewmodel.AuthViewModel
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens
import com.deepholistics.res.AppDimens.spacingLg
import com.deepholistics.res.AppDimens.spacingMd
import com.deepholistics.res.AppDimens.textSizeXl
import humantokenv2.composeapp.generated.resources.Res
import humantokenv2.composeapp.generated.resources.app_name
import humantokenv2.composeapp.generated.resources.back
import humantokenv2.composeapp.generated.resources.ht_logo_196
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview()
@Composable
fun HealthProfileScreenPreview() {
    // Provide a fake or mock AuthViewModel for preview purposes
    val fakeAuthViewModel = remember {
        object : AuthViewModel() {
            // Override necessary methods if required for preview
        }
    }

    HealthProfileScreen(
        authViewModel = fakeAuthViewModel, onNavigateToSampleCollection = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthProfileScreen(
    authViewModel: AuthViewModel, onNavigateToSampleCollection: () -> Unit
) {

    val sampleCollectionState by authViewModel.sampleCollectionState.collectAsState()
    var dateOfBirth by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LaunchedEffect(sampleCollectionState) {
        authViewModel.sampleCollectionValidated()
    }

    ScreenBackground {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(Res.string.app_name),
                                color = AppColors.TextPrimary,
                                fontSize = textSizeXl,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }, navigationIcon = {
                        IconButton(onClick = { /* Handle back navigation */ }) {
                            Icon(
                                painter = painterResource(Res.drawable.ht_logo_196),
                                contentDescription = stringResource(Res.string.back),
                                tint = AppColors.TextPrimary
                            )
                        }
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = AppColors.SurfaceDark
                    )
                )
            },
            containerColor = AppColors.Transparent,
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
                    .padding(horizontal = spacingMd).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(spacingLg)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Your Health Profile",
                        color = AppColors.TextPrimary,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,

                        modifier = Modifier.padding(bottom = 32.dp, top = 32.dp)
                    )

                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        label = { Text("Date of Birth") },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        label = { Text("Gender") },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { dateOfBirth = it },
                        label = { Text("Weight") },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { dateOfBirth = it },
                        label = { Text("Height") },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true
                    )

                    if (weight.isNotEmpty() && gender != weight) {
                        Text(
                            text = "Passwords do not match",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    PrimaryButton(
                        modifier = Modifier.padding(vertical = AppDimens.paddingValues),
                        buttonName = "Continue",
                        onClick = {
                            scope.launch {
                                if (gender == weight && gender.isNotEmpty() && dateOfBirth.isNotEmpty() && height.isNotEmpty()) {
                                    scope.launch {
                                        withContext(Dispatchers.IO) {
                                            authViewModel.createAccount(dateOfBirth, gender, height)
                                        }
                                    }
                                }
                            }
                        },
                        enable = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
