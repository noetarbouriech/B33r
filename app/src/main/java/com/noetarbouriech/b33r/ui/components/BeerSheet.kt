package com.noetarbouriech.b33r.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerSheet(showBottomSheet: MutableState<Boolean>) {
    val sheetState = rememberModalBottomSheetState()
    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState
        ) {
            var selectedIndex by remember { mutableIntStateOf(0) }
            val options = listOf("∅", "Planning", "Tried")
            var sliderPosition by remember { mutableFloatStateOf(0f) }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 64.dp)
            ) {
                Text("Beer Status", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))
                SingleChoiceSegmentedButtonRow {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                            onClick = { selectedIndex = index },
                            selected = index == selectedIndex
                        ) {
                            Text(label)
                        }
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                Slider(
                    enabled = selectedIndex == 2,
                    value = sliderPosition,
                    steps = 4,
                    valueRange = 0f..5f,
                    onValueChange = { sliderPosition = it },
                    modifier = Modifier.padding(bottom = 8.dp).padding(horizontal = 8.dp)
                )
                Text(
                    "${if (selectedIndex != 2) "?" else sliderPosition.roundToInt()}" + " / 5",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }
}