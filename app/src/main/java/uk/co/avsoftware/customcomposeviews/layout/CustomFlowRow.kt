package uk.co.avsoftware.customcomposeviews.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable

@Composable
fun CustomFlowRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Layout(
        modifier = modifier,
        measurePolicy = {
            measurables, constraints ->
                // measure all placeables against the current constraints
                val placeables = measurables.map {
                    it.measure(constraints)
                }

            // group placeables into rows
            val groupedPlaceables = mutableListOf<List<Placeable>>()
            var currentGroup = mutableListOf<Placeable>()
            var currentGroupWidth = 0

            // iterate through the placeables
            placeables.forEach { placeable ->
                if (currentGroupWidth + placeable.width <= constraints.maxWidth){
                    currentGroup.add(placeable)
                    currentGroupWidth += placeable.width
                } else {
                    currentGroup.add(placeable)
                    // jump to next row
                    groupedPlaceables.add(currentGroup)
                    currentGroup = mutableListOf()
                    currentGroupWidth = placeable.width
                }
            }

            if (currentGroup.isNotEmpty()){
                groupedPlaceables.add(currentGroup)
            }

            layout( width = constraints.maxWidth, height = constraints.maxHeight){
                var yPosition = 0
                groupedPlaceables.forEach {
                    row ->
                    var xPosition = 0
                    row.forEach { placeable ->
                        placeable.place(xPosition, yPosition)
                        xPosition += placeable.width
                    }
                    // increment y position
                    yPosition += row.maxOfOrNull { it.height } ?: 0
                }
            }
        },
        content = content
    )
}