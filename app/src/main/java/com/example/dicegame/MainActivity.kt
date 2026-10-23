package com.example.dicegame
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicegame.ui.theme.DiceGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                DiceScreen()
            }
        }
    }
}

@Composable
fun DiceScreen(modifier: Modifier=Modifier){
    val check = remember { mutableStateListOf(false,false,false,false,false) }
    val values = remember {mutableStateListOf(5,5,5,5,5)}
    var rolls by remember { mutableIntStateOf(0) }
    var scores by remember {mutableStateOf(listOf<Int>())}
    scores = mutableListOf(0,0,0,0,0,0,0,0,0,0,0,0,0)
    Dice(values,check,scores,
        onCheckChange = { index, checked -> check[index] = checked },
        onRollClick = {
            if(rolls<3){
                ++rolls
                for (i in values.indices) {
                    if (!check[i]) {
                        values[i] = rollDie()
                    }
                }
                scores = scoreCalc(values)


            }

        },
        rolls,
        modifier=modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DicePreview() {
    DiceGameTheme {
        DiceScreen()
    }
}

@Composable
fun Dice(values: List<Int>,
         check: List<Boolean>,
         scores: List<Int>,
         onCheckChange: (index: Int, checked: Boolean) -> Unit,
         onRollClick: () -> Unit,
         rolls: Int,
         modifier: Modifier = Modifier
) {
    val dieArray = remember {
        intArrayOf(
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
        )
    }
    Column(
        modifier = modifier.fillMaxSize(),
// verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Dice Image Row 1
        Row(){
            Image(
                painter = painterResource(id = dieArray[values[0]]),
                contentDescription = "Dice image",
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = dieArray[values[1]]),
                contentDescription = "Dice image",
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    .size(100.dp)
            )
        }
        //Keep Buttons Row 1
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Keep?", fontSize = 24.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(
                checked = check[0],
                onCheckedChange = {onCheckChange(0,it)}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(
                checked = check[1],
                onCheckedChange = {onCheckChange(1,it)}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("Keep?", fontSize = 24.sp)
        }
            //Dice Image Row 2
        Row(){
            Image(
                painter = painterResource(id = dieArray[values[2]]),
                contentDescription = "Dice image",
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = dieArray[values[3]]),
                contentDescription = "Dice image",
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    .size(100.dp)
            )
        }
        //Keep Buttons Row 2
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Keep?", fontSize = 24.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(
                checked = check[2],
                onCheckedChange = {onCheckChange(2,it)}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(
                checked = check[3],
                onCheckedChange = {onCheckChange(3,it)}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("Keep?", fontSize = 24.sp)

        }
        //Dice Image Row 3
        Row(){
            Image(
                painter = painterResource(id = dieArray[values[4]]),
                contentDescription = "Dice image",
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    .size(100.dp)
            )
        }
        //Keep Buttons Row 3
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Keep?", fontSize = 24.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(
                checked = check[4],
                onCheckedChange = {onCheckChange(4,it)}
            )
        }

        //Roll them all button
        Row(){
            Spacer(modifier = Modifier.height(5.dp))
            Button(modifier=Modifier.fillMaxWidth(),onClick = onRollClick)
            { Text("Roll Dice") }
            Spacer(modifier = Modifier.height(5.dp))
        }
        //Stupid moron row take 2 :)
        Row(){

            Text("$scores")
            EvolvingDropdown(scores)
        }
        //Stupid moron row take 3 :)
        Row(){
            Text("$rolls")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvolvingDropdown(scores: List<Int>){
    val remainingOptions = remember {mutableStateListOf<String>().apply {addAll((1..13).map {"Score for this option is: ${scores[it-1]}"})}}
    val picked = remember {mutableStateListOf<String>()}
    var expanded by remember {mutableStateOf(false)}
    Column(Modifier.padding(16.dp)){
        ExposedDropdownMenuBox(expanded=expanded, onExpandedChange={expanded=it}){
            OutlinedTextField(
                value = "Select an option (${remainingOptions.size} left)",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded)},
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded= expanded, onDismissRequest = {expanded= false}) {
                remainingOptions.toList().forEach { option ->
                    DropdownMenuItem(
                        text = {Text(option)},
                        onClick = {
                            remainingOptions.remove(option)
                            picked.add(option)
                            expanded = false
                        }
                    )
                }
            }
        }
        picked.forEach {Text(it,Modifier.padding(top=8.dp))}
    }
}

//Utility Functions
fun scoreCalc(values: List<Int>):List<Int>{
    val scores = mutableListOf(0,0,0,0,0,0,0,0,0,0,0,0,0)
    val totalDice = values.sum() + 5
    val sortedDistinct = values.distinct().sorted()

    //Aces through Sixes
    for (i in scores.indices.take(6)) {
        scores[i] = values.count { it == i } * (i+1)
    }

    // 3 of a kind
    val hasThreeOfAKind = values.groupingBy { it }.eachCount().values.any { it >= 3 }
    if(hasThreeOfAKind){
        scores[6] = totalDice
    }

    //4 of a Kind
    val has4OfAKind = values.groupingBy { it }.eachCount().values.any { it >= 4 }
    if(has4OfAKind){
        scores[7] = totalDice
    }

    //Full House
    val counts = values.groupingBy { it }.eachCount().values.sorted()
    if(counts == listOf(2, 3)){
        scores[8] = 25
    }


    //Small Straight
    val smallS1 = listOf(1,2,3,4)
    val smallS2 = listOf(2,3,4,5)
    val smallS3 = listOf(3,4,5,6)
    if(sortedDistinct.size == 4){
        if(sortedDistinct==smallS1||sortedDistinct==smallS2||sortedDistinct==smallS3){
            scores[9] = 30
        }
    }
    //Large Straight
    val largeS1 = listOf(1,2,3,4,5)
    val largeS2 = listOf(2,3,4,5,6)
    if(sortedDistinct.size==5){
        if(sortedDistinct==largeS1 || sortedDistinct== largeS2){
            scores[10] = 40
        }
    }

    //Yahtzee
    val hasYahtzee = values.groupingBy { it }.eachCount().values.any { it == 5 }
    if(hasYahtzee){
        scores[11] = 50
    }

    //Chance
    scores[12] = totalDice

    return scores
}

fun rollDie():Int{

    val roll=(0..5).random()

    return roll

}

