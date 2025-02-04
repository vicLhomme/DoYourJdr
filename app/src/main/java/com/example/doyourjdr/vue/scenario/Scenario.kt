package com.example.doyourjdr.vue.scenario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.doyourjdr.R
import com.example.doyourjdr.data.room.ScenarioDao
import com.example.doyourjdr.data.room.ScenarioDatabase
import com.example.doyourjdr.data.room.ScenarioEntity
import com.example.doyourjdr.ui.theme.DoYourJDRTheme
import com.example.doyourjdr.vue.MainActivity
import com.example.doyourjdr.vue.fonctionscommunes.AfficheContenu
import com.example.doyourjdr.vue.fonctionscommunes.AfficheFiltreFond
import com.example.doyourjdr.vue.fonctionscommunes.AfficheImageFond
import com.example.doyourjdr.vue.fonctionscommunes.BoutonClassique
import com.example.doyourjdr.vue.fonctionscommunes.KCObraLetra
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class Scenario() : ComponentActivity() {
    private lateinit var innerPadding: PaddingValues
    private lateinit var db: ScenarioDatabase
    private lateinit var scenarioDao: ScenarioDao



    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = ScenarioDatabase.getDatabase(this)
        scenarioDao = db.scenarioDao()
        val scenario = ScenarioEntity(libelle = "Un dernier Scénario", avancement = 1)
        GlobalScope.launch(Dispatchers.IO) {
            scenarioDao.insert(scenario)
        }

        GlobalScope.launch(Dispatchers.Main) {
            val scenarios = withContext(Dispatchers.IO) {
                scenarioDao.getAllScenario()
            }
            scenarios.forEach {
                println(it)
            }
        }
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

            AfficheContenu(
                finishRetour = { finish() }, // indique si le bouton retour clot l'activité
                routeRetour = MainActivity::class.java, // indique la route du bouton retour
                composantInterne = { AfficheContenuInterne() })


    }


    @Composable
    private fun AfficheContenuInterne() {


        val listeScenario: List<ScenarioEntity> =
            mutableListOf()// = scenarioViewModel.getScenarios()


        ConstraintLayout(
            Modifier.fillMaxSize()
        ) {
            val (entete, corps, enpieds) = createRefs()
            AfficheContenuEntete(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    //.background(Color.Cyan)
                    .constrainAs(entete) {
                        top.linkTo(parent.top)
                        bottom.linkTo(corps.top)
                    },
            )
            if (listeScenario.isEmpty()) {
                AfficheAucunScenario(
                    modifier = Modifier
                        .fillMaxHeight(0.85f)
                        .constrainAs(corps) {
                            top.linkTo(entete.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            } else {
                AfficheScenarios(listeScenario, entete)
            }

        }
    }

    @Composable
    private fun AfficheContenuEntete(modifier: Modifier) {
        ConstraintLayout(
            modifier = modifier,

            ) {
            val (composantText) = createRefs()
            Text(
                text = "Scénario",
                modifier = Modifier
                    .constrainAs(composantText) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = KCObraLetra
            )
        }
    }

    private @Composable
    fun AfficheAucunScenario(modifier: Modifier) {

        Column(
            modifier = modifier
                .clip(RoundedCornerShape(70f))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF703F36))
                    .padding(8.dp)
                    .border(5.dp, Color(0xFFA6730F), RoundedCornerShape(12.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Aucun scénario trouvé")
                Text("Créer un scénario")
            }
            Spacer(
                modifier = Modifier.fillMaxHeight(0.25f)
            )
            BoutonClassique(
                contentDescription = "Continuer",
                imageRessource = R.drawable.marteau,
                rotationImage = 20f,
                modifier = Modifier
                    .fillMaxSize(0.45f)
            ) {
                println("Continuer")
            }
        }
    }

    private @Composable
    fun AfficheScenarios(
        listeScenario: List<ScenarioEntity>,
        entete: ConstrainedLayoutReference
    ) {
        Text("Au moins 1 scénario")
    }

    @Composable
    private fun AfficheContenuEnPied(modifier: Modifier) {
        Box(modifier = modifier.background(Color.White))
    }


}











