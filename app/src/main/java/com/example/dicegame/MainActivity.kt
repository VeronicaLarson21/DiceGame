package com.example.dicegame
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
        //Keep Buttons
        Row(){
            Spacer(modifier = Modifier.height(5.dp))
            Checkbox(
                checked = check[0],
                onCheckedChange = {onCheckChange(0,it)}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(
                checked = check[1],
                onCheckedChange = {onCheckChange(1,it)}
            )
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
        Row(){
            Spacer(modifier = Modifier.height(5.dp))
            Checkbox(
                checked = check[2],
                onCheckedChange = {onCheckChange(2,it)}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(
                checked = check[3],
                onCheckedChange = {onCheckChange(3,it)}
            )
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
        Row(){
            Spacer(modifier = Modifier.height(5.dp))
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
    }
}







fun rollDie():Int{

    val roll=(0..5).random()

    return roll

}

