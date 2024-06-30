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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noetarbouriech.b33r.data.SavedBeer
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerSheet(showBottomSheet: MutableState<Boolean>, beer: SavedBeer?, setNull: () -> Unit, setPlanning: () -> Unit, setTried: (Int) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val options = listOf("∅", "Planning", "Tried")

    // Remember slider position and selected index
    var sliderPosition by remember { mutableStateOf(0f) }
    var selectedIndex by remember { mutableStateOf(0) }

    // Update state when beer changes
    LaunchedEffect(beer) {
        if (beer != null) {
            selectedIndex = if (beer.planning) 1 else 2
            sliderPosition = beer.score?.toFloat() ?: 0f
        } else {
            selectedIndex = 0
            sliderPosition = 0f
        }
    }
    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState
        ) {
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
                            onClick = {
                                selectedIndex = index
                                when (selectedIndex) {
                                    0 -> setNull()
                                    1 -> setPlanning()
                                    2 -> setTried(sliderPosition.roundToInt())
                                }},
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
