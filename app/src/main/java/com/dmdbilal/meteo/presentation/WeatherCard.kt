package com.dmdbilal.meteo.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import com.dmdbilal.meteo.R

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {

    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            modifier = modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = backgroundColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                // Weather icon
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Weather Description
                Text(
                    text = data.weatherType.weatherDesc,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Current day with time
                Text(
                    text = "Today ${data.time.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    )}",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.height(30.dp))
                // Temperature
                Text(
                    text = "${data.temperature}°C",
                    fontSize = 70.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.humidity.roundToInt(),
                        unit = " %",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = " km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        textStyle = TextStyle(color = Color.White)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }


}