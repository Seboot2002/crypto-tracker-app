package com.sebascamayo.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

data class ChartStyle(
    val chartLineColor: Color, // color de las lineas del grafico
    val unselectedColor: Color, // color de punto deseleccionado
    val selectedColor: Color, // color de punto seleccionado
    val helperLinesThicknessPx: Float, // ancho de lines cuadradas paradas y echadas en el grafico
    val axisLinesThicknessPx: Float, // ancho de lineas de x / y
    val labelFontSize: TextUnit,// tamaño de los labels en el grafico
    val minYLabelSpacing: Dp, // espacio entre los labels en el eje y
    val verticalPadding: Dp, // espacio más allá de los labels en vertical
    val horizontalPadding: Dp, // espacio más allá de los labels en horizontal
    val xAxisLabelSpacing: Dp // espacio entre los labels en el eje x
)
