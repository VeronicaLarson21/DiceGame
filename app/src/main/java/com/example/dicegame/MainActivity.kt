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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
    Dice(values,check,
        onCheckChange = { index, checked -> check[index] = checked },
        onRollClick = {
            for (i in values.indices) {
                if (!check[i]) {
                    values[i] = rollDie()
                }
            }
        },
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
         onCheckChange: (index: Int, checked: Boolean) -> Unit,
         onRollClick: () -> Unit,
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
            val test = scoreCalc(values)
            Text("$test")
        }
    }
}

fun scoreCalc(values: List<Int>):List<Int>{
    val scores = mutableListOf(0,0,0,0,0,0,0,0,0,0,0,0,0)
    val totalDice = scores.sum()
    val sortedDistinct = scores.distinct().sorted()

    //Aces through Sixes
    for (i in scores.indices.take(6)) {
        scores[i] = values.count { it == i }
    }

    // 3 of a kind
    val hasThreeOfAKind = values.groupingBy { it }.eachCount().values.any { it == 3 }
    if(hasThreeOfAKind){
        scores[6] = totalDice
    }

    //4 of a Kind
    val has4OfAKind = values.groupingBy { it }.eachCount().values.any { it == 4 }
    if(has4OfAKind){
        scores[7] = totalDice
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

    //Full House
    if(hasThreeOfAKind && !has4OfAKind && !hasYahtzee){
        //check to see if you have a double
    }

    //Chance
    scores[12] = totalDice





    return scores
}





fun rollDie():Int{

    val roll=(0..5).random()

    return roll

}

