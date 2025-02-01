package com.example.doyourjdr.vue.scenario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.doyourjdr.ui.theme.DoYourJDRTheme
import com.example.doyourjdr.vue.MainActivity
import com.example.doyourjdr.vue.fonctionscommunes.AfficheContenu
import com.example.doyourjdr.vue.fonctionscommunes.AfficheImageFond
import com.example.doyourjdr.vue.fonctionscommunes.AfficheFiltreFond

class Scenario : ComponentActivity() {
    private lateinit var innerPadding: PaddingValues
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoYourJDRTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    this.innerPadding = innerPadding
                    ConstructionComposant()
                }
            }
        }
    }


    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    private fun ConstructionComposant() {
        println("SECONDE ACTIVITE")
        AfficheImageFond(2.7f, 2.7f, TransformOrigin(0.09f, 0.42f))
        AfficheFiltreFond()
        AfficheContenu(finishRetour = { finish() }, // indique si le bouton retour clot l'activité
            routeRetour = MainActivity::class.java, // indique la route du bouton retour
            contenuCorps = { AfficheContenu() })
    }

    @Composable
    private fun AfficheContenu() {
        ConstraintLayout(
            Modifier
                .fillMaxSize()

        ) {
            val (entete, corps, enpieds) = createRefs()
            val topLimite = createGuidelineFromTop(0.5f)
            AfficheContenuEntete(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .constrainAs(entete) {
                        top.linkTo(parent.top)
                        bottom.linkTo(corps.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            )
            AfficheContenuCorps(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .constrainAs(corps) {
                        top.linkTo(entete.bottom)
                        bottom.linkTo(enpieds.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            )
            AfficheContenuEnPied(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .constrainAs(enpieds) {
                        top.linkTo(corps.bottom)
                        bottom.linkTo(corps.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            )
        }
    }


    @Composable
    private fun AfficheContenuEntete(modifier: Modifier) {
         ConstraintLayout(
             modifier = modifier.background(Color.Red)
         ) {
             val (composantText) = createRefs()
             Text(
                 text = "Scénario",
                 modifier = Modifier
                     .constrainAs(composantText){
                         top.linkTo(parent.top)
                         bottom.linkTo(parent.bottom)
                         start.linkTo(parent.start)
                         end.linkTo(parent.end)
                     }
                     .background(Color.Green)
             )
         }
    }
    @Composable
    private fun AfficheContenuCorps(modifier: Modifier) {
        Box(modifier = modifier.background(Color.Gray))
    }

    @Composable
    private fun AfficheContenuEnPied(modifier: Modifier) {
        Box(modifier = modifier.background(Color.White))
    }


}











