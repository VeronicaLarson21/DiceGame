package com.example.dicegame
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
    var dieIndex by remember { mutableIntStateOf(5) }
    Dice(dieIndex,
        onRollClick={
            dieIndex=rollDie()
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
fun Dice(dieIndex:Int,
         onRollClick: ()->Unit,
         modifier: Modifier= Modifier
){





    val dieArray=remember {
        intArrayOf(

            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
        )
    }
// Rotation animation







    Column(

        modifier = modifier.fillMaxSize(),

// verticalArrangement = Arrangement.SpaceEvenly,

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(modifier = Modifier.height(24.dp))



        Image(

            painter= painterResource(id=dieArray[dieIndex]),

            contentDescription = "Dice image",

            modifier = Modifier

                .size(150.dp)





        )

        Spacer(modifier = Modifier.height(24.dp))



        Button(onClick=onRollClick)

        { Text("Roll Die")}

        Spacer(modifier = Modifier.height(24.dp))

        val dieValue=dieIndex+1

        Text("$dieValue",
            fontSize = 32.sp)

    }

}





fun rollDie():Int{

    val roll=(0..5).random()

    return roll

}

