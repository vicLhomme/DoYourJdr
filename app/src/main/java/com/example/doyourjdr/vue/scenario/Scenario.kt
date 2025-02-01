package com.example.doyourjdr.vue.scenario

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.doyourjdr.R
import com.example.doyourjdr.ui.theme.DoYourJDRTheme
import com.example.doyourjdr.vue.MainActivity
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
        AfficheImageFond(2.7f, 2.7f, TransformOrigin(0f, 0.3f))
        AfficheFiltreFond()
        AfficheContenu()
    }

    @Composable
    fun AfficheContenu() {
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)) {
            val (entete, corps, enpieds) = createRefs()
            AfficheEntete(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .constrainAs(entete) {
                        top.linkTo(parent.top)
                        bottom.linkTo(corps.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                route = MainActivity::class.java
            )
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
    fun AfficheEntete(modifier: Modifier, route: Class<out Activity>? = null) {
        val context = LocalContext.current
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.epee_retour_simple),
                contentDescription = "BoutonRetour",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .rotate(-90f)
                    .clickable {
                        if (route != null) {
                            val intent = Intent(context, route)
                            context.startActivity(intent)
                        }
                    }
            )
        }
    }

    @Composable
    private fun AfficheCorps(modifier: Modifier) {
        Column(modifier = modifier.background(Color.Red)) { }
    }

    @Composable
    private fun AfficheEnPied(modifier: Modifier) {
        Row(modifier = modifier.background(Color.Green)) { }
    }
}





