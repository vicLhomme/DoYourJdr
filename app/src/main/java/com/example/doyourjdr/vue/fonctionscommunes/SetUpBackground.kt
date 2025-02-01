package com.example.doyourjdr.vue.fonctionscommunes

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.doyourjdr.R


@Composable
fun AfficheImageFond(
    _scaleX: Float,
    _scaleY: Float,
    _transformOrigin: TransformOrigin = TransformOrigin(0.5f, 0.5f)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .clipToBounds(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondecran),
            contentDescription = "Une image depuis drawable",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = _scaleX
                    scaleY = _scaleY
                    transformOrigin = _transformOrigin
                }

        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AfficheFiltreFond() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Top + WindowInsetsSides.Bottom)
                    .asPaddingValues()
            ) // Respecte uniquement le haut et le bas
            .background(Color(0x99000000))
            .clipToBounds()
    )
}

@Composable
fun AfficheContenu(
    finishRetour: () -> Unit,
    routeRetour: Class<out Activity>?,
    composantInterne: @Composable () -> Unit,
) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        val (entete, corps, enpieds) = createRefs()
        val heightEntete = 0.1f
        AfficheEntete(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(heightEntete)
                .constrainAs(entete) {
                    bottom.linkTo(corps.top)
                },
            route = routeRetour,
            finishActivity = finishRetour

        )
        AfficheCorps(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .constrainAs(corps) {
                    top.linkTo(entete.bottom)
                },
            composantInterne = composantInterne
        )
        AfficheEnPied(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .constrainAs(enpieds) {
                    top.linkTo(corps.bottom)
                })
    }
}

@Composable
fun AfficheEntete(
    modifier: Modifier,
    route: Class<out Activity>? = null,
    finishActivity: (() -> Unit)? = null
) {
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
                        if (finishActivity != null) {
                            finishActivity()
                        }

                    }
                }

        )
    }
}

@Composable
private fun AfficheCorps(modifier: Modifier, composantInterne: @Composable () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.92f),
            ) {
            Image(
                painter = painterResource(R.drawable.feuille_fripee),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(70f))
                    .graphicsLayer {
                        scaleX = 1.2f
                        scaleY = 1.2f
                    },
                contentScale = ContentScale.FillBounds
            )
            composantInterne()
            // contenu du corps

        }
    }
}

@Composable
private fun AfficheEnPied(modifier: Modifier) {

}


