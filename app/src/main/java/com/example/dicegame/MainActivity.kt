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
    var dieIndex1 by remember { mutableIntStateOf(5) }
    var dieIndex2 by remember { mutableIntStateOf(5) }
    var dieIndex3 by remember { mutableIntStateOf(5) }
    var dieIndex4 by remember { mutableIntStateOf(5) }
    var dieIndex5 by remember { mutableIntStateOf(5) }
    Dice(dieIndex1, dieIndex2, dieIndex3, dieIndex4, dieIndex5,
        onRollClick={
            dieIndex1=rollDie()
            dieIndex2=rollDie()
            dieIndex3=rollDie()
            dieIndex4=rollDie()
            dieIndex5=rollDie()
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
fun Dice(dieIndex1:Int,
         dieIndex2:Int,
         dieIndex3:Int,
         dieIndex4:Int,
         dieIndex5:Int,
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

            painter= painterResource(id=dieArray[dieIndex1]),

            contentDescription = "Dice image",

            modifier = Modifier

                .size(150.dp)





        )

        Spacer(modifier = Modifier.height(24.dp))



        Button(onClick=onRollClick)

        { Text("Roll Die")}

        Spacer(modifier = Modifier.height(24.dp))

        val dieValue1=dieIndex1+1

        Text("$dieValue1",
            fontSize = 32.sp)

        Spacer(modifier = Modifier.height(24.dp))



        Image(

            painter= painterResource(id=dieArray[dieIndex2]),

            contentDescription = "Dice image",

            modifier = Modifier

                .size(150.dp)





        )

        Spacer(modifier = Modifier.height(24.dp))



        Button(onClick=onRollClick)

        { Text("Roll Die")}

        Spacer(modifier = Modifier.height(24.dp))

        val dieValue2=dieIndex2+1

        Text("$dieValue2",
            fontSize = 32.sp)

    }

}





fun rollDie():Int{

    val roll=(0..5).random()

    return roll

}

