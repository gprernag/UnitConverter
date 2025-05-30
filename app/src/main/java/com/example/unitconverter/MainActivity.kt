package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitConverter()
            }
        }
    }
    }


@Composable
fun UnitConverter() {
    var inputValue by remember {mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Centimeters")}
    var outputUnit by remember {mutableStateOf("Meters")}
    var iExpanded by remember {mutableStateOf(false)}
    var oExpanded by remember {mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oconversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){
        // ?: elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value* 100.0 / oconversionFactor.value ).roundToInt()/100.0
        outputValue = result.toString()
    }
    val lightLavender = Color(0xFFF5F0FF) // Very light lavender background
    val mediumLavender = Color(0xFFE6E6FA) // Base lavender
    val darkLavender = Color(0xFFB57EDC) // Darker lavender for accents
    val pinkLavender = Color(0xFFD8BFD8) // Pinkish lavender
    val deepPurple = Color(0xFF9370DB) // Deep purple for text

//    Scaffold { innerPadding ->
//        Column(modifier = Modifier.padding(innerPadding)) {
    Column(
        modifier = Modifier
            .fillMaxSize().
            background(lightLavender)
                .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

            Text(
                "Unit Converter",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = deepPurple,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 32.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = inputValue ,
                onValueChange = {
                inputValue=it
                convertUnits()
            //What should happen when value of our outlined code changes
                },
                label={Text("Enter Value")},
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                //InputBox
                Box(
                    modifier = Modifier.weight(1f) // This makes both buttons share equal width
                ){
                    //InputButton
                    Button(
                        onClick = { iExpanded = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = mediumLavender,
                            contentColor = deepPurple
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = inputUnit,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = deepPurple
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                    }
                    //expanded = false - initially the drop down menu is closed
                    DropdownMenu(
                        expanded = iExpanded,
                        onDismissRequest = {iExpanded = false},
                        modifier = Modifier.background(lightLavender)
                    ) {
                        DropdownMenuItem(
                            text = {Text("Centimeters", color = deepPurple)},
                            onClick = {
                                iExpanded = false
                                inputUnit="Centimeters"
                                conversionFactor.value = 0.01
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = {Text("Meters", color = deepPurple)},
                            onClick = {
                               iExpanded = false
                                inputUnit = "Meters"
                                conversionFactor.value = 1.0
                                convertUnits()

                            }
                        )
                        DropdownMenuItem(
                            text = {Text("Milimeters", color = deepPurple)},
                            onClick = {
                                iExpanded=false
                                inputUnit = "Milimeters"
                                conversionFactor.value = 0.001
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = {Text("Feet", color = deepPurple)},
                            onClick = {
                                iExpanded=false
                                inputUnit="Feet"
                                conversionFactor.value = 0.3048
                                convertUnits()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier.weight(1f) // This makes both buttons share equal width
                ){
                    Button(
                        onClick = { oExpanded = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = mediumLavender,
                            contentColor = deepPurple
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = outputUnit,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,

                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                    }
                    DropdownMenu(
                        expanded = oExpanded,
                        onDismissRequest = {oExpanded=false},
                        modifier = Modifier.background(lightLavender)
                    ) {
                        DropdownMenuItem(
                            text = {Text("Centimeters", color = deepPurple)},
                            onClick = {
                                oExpanded=false
                                outputUnit="Centimeters"
                                oconversionFactor.value = 0.01
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = {Text("Meters", color = deepPurple)},
                            onClick = {
                                oExpanded=false
                                outputUnit="Meters"
                                oconversionFactor.value = 1.00
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = {Text("Milimeters", color = deepPurple)},
                            onClick = {
                                oExpanded=false
                                outputUnit="Milimeters"
                                oconversionFactor.value = 0.001
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = {Text("Feet", color = deepPurple)},
                            onClick = {
                                oExpanded=false
                                outputUnit="Feet"
                                oconversionFactor.value = 0.3048
                                convertUnits()

                            }
                        )
                    }
                }
            }
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .background(pinkLavender, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                "Result: $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {

    }
}