package com.example.doyourjdr.vue.fonctionscommunes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.doyourjdr.R



@Composable
fun AfficheImageFond(
    _scaleX : Float,
    _scaleY : Float,
    _transformOrigin : TransformOrigin = TransformOrigin(0.5f, 0.5f)
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
            .padding(WindowInsets.systemBars.only(WindowInsetsSides.Top + WindowInsetsSides.Bottom).asPaddingValues()) // Respecte uniquement le haut et le bas
            .background(Color(0x66000000))
            .clipToBounds()
    )
}

