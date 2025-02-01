package com.example.doyourjdr.vue

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doyourjdr.ui.theme.DoYourJDRTheme
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.doyourjdr.R
import com.example.doyourjdr.vue.fonctionscommunes.AfficheImageFond
import com.example.doyourjdr.vue.scenario.Scenario


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ConstructionComposant()
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    private fun ConstructionComposant() {
        println("JE SUIS A LA PREMIERE ACTIVITE")
        DoYourJDRTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                AfficheImageFond( 1.7f, 1.7f)
                AfficheBouton()
            }
        }
    }

    @Composable
    fun AfficheBouton() {
        val modifierBorder = Modifier.border(2.dp, Color(0x016D490B))
        val background = Modifier.background(Color.DarkGray)
        val modifierBackground = Modifier.background(Color(0xAC505050))
        val topTitre = 0.15f
        val fontSizeLittle = 18
        val fontSizeTitle = 22
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (titre, scenario, continuer, carte, personnage) = createRefs()
            val topLimite = createGuidelineFromTop(topTitre)
            val midLimite = createGuidelineFromTop(topTitre + 0.27f)
            val startLimite = createGuidelineFromStart(-0.5f)
            val endLimite = createGuidelineFromStart(0.5f)

            ContainerBox(
                modifier = Modifier
                    .fillMaxWidth(0.42f)
                    .aspectRatio(1f)
                    .constrainAs(titre) {
                        top.linkTo(topLimite)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                composant = {
                    RotatedTitleWithText(
                        "DoYourJdr",
                        modifierBorder.then(background),
                        fontSizeTitle
                    )
                },
            )

            ContainerBox(
                modifier = Modifier
                    .fillMaxWidth(0.29f)
                    .aspectRatio(1f)
                    .constrainAs(scenario) {
                        top.linkTo(midLimite)
                        start.linkTo(startLimite)
                        end.linkTo(parent.end)
                    },
                composant = {
                    RotatedBoxWithText(
                        titre ="Scénario",
                        modifier = modifierBorder.then(modifierBackground),
                        fontSize = fontSizeLittle,
                        route = Scenario::class.java
                    )
                }
            )
            ContainerBox(
                modifier = Modifier
                    .fillMaxWidth(0.29f)
                    .aspectRatio(1f)
                    .constrainAs(carte) {
                        top.linkTo(midLimite)
                        start.linkTo(endLimite)
                        end.linkTo(parent.end)
                    },
                composant = {
                    RotatedBoxWithText(
                        "Carte",
                        modifierBorder.then(modifierBackground),
                        fontSizeLittle
                    )
                }
            )
            ContainerBox(
                modifier = Modifier
                    .fillMaxWidth(0.38f)
                    .aspectRatio(1f)
                    .constrainAs(continuer) {
                        top.linkTo(carte.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                composant = {
                    RotatedBoxWithText(
                        "Continuer la partie",
                        modifierBorder.then(modifierBackground),
                        fontSizeTitle
                    )
                }
            )
            ContainerBox(
                modifier = Modifier
                    .fillMaxWidth(0.29f)
                    .aspectRatio(1f)
                    .constrainAs(personnage) {
                        top.linkTo(continuer.bottom)
                        start.linkTo(startLimite)
                        end.linkTo(parent.end)
                    },
                composant = {
                    RotatedBoxWithText(
                        "Personnage",
                        modifierBorder.then(modifierBackground),
                        fontSizeLittle,

                        )
                }
            )
        }
    }

    @Composable
    private fun ContainerBox(
        modifier: Modifier,
        composant: @Composable () -> Unit,
    ) {
        Box(modifier = modifier) {
            composant()
        }
    }

    @Composable
    private fun RotatedTitleWithText(titre: String, modifier: Modifier, fontSize: Int) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { rotationZ = 45f }
                .then(modifier),
        ) {
            Image(
                painter = painterResource(id = R.drawable.muraille),
                contentDescription = "Une image depuis drawable",
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .offset(y = (-35).dp),
                contentScale = ContentScale.FillWidth
            )
            Image(
                painter = painterResource(id = R.drawable.muraille),
                contentDescription = "Une image depuis drawable",
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .graphicsLayer { rotationZ = -90f }
                    .align(Alignment.CenterStart)
                    .offset(y = -90.dp),
                contentScale = ContentScale.FillWidth

            )
            Column(modifier = Modifier
                .fillMaxWidth(1f)
                .graphicsLayer { rotationZ = -45f }
                .aspectRatio(1f)) {
                Column(modifier = Modifier.fillMaxHeight(0.55f)) {
                    Box(modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth())
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        text = "8",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = (fontSize * 2).sp
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    thickness = 4.dp,
                    color = Color(0xFF606060),

                    )
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .fillMaxWidth())
                    Text(
                        text = titre,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize.sp
                    )
                }

            }
        }
    }


    @Composable
    private fun RotatedBoxWithText(titre: String, modifier: Modifier, fontSize: Int, route: Class<out Activity>? = null) {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .graphicsLayer { rotationZ = 45f }
                .border(5.dp, Color(0xFF6D490B))
                .then(modifier)
                .clickable {
                    if (route != null) {
                        val intent = Intent(context, route)
                        context.startActivity(intent)
                    }
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .then(modifier),
            ) {
                Text(
                    text = titre,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer { rotationZ = -45f }
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize.sp
                )
            }
        }
    }
}



