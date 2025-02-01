package com.example.doyourjdr.vue.scenario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.doyourjdr.ui.theme.DoYourJDRTheme
import com.example.doyourjdr.vue.fonctionscommunes.AfficheImageFond
import com.example.doyourjdr.vue.fonctionscommunes.AfficheFiltreFond

class Scenario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoYourJDRTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConstructionComposant()
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ConstructionComposant() {
    println("SECONDE ACTIVITE")
    AfficheImageFond(2.7f, 2.7f, TransformOrigin(0f,0.3f))
    AfficheFiltreFond()
    AfficheContenu()
}

@Composable
fun AfficheContenu() {
    ConstraintLayout(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)){
        val (entete, corps, enpieds ) = createRefs()
        AfficheEntete(
            modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .constrainAs(entete) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        AfficheCorps(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.82f)
                .constrainAs(corps) {
                    top.linkTo(entete.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        AfficheEnPied(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.08f)
                .constrainAs(enpieds) {
                    top.linkTo(corps.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
    }
}



@Composable
private fun AfficheEntete(modifier: Modifier) {
    Row(modifier = modifier.background(Color.Blue)) {  }
}

@Composable
private fun AfficheCorps(modifier: Modifier){
    Column(modifier = modifier.background(Color.Red)) {  }
}

@Composable
private fun AfficheEnPied(modifier: Modifier) {
    Row(modifier = modifier.background(Color.Green)) {  }
}


