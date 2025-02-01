package com.example.doyourjdr.vue.fonctionscommunes

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.doyourjdr.ui.theme.ColorButton

@Composable
fun BoutonClassique(
    contentDescription: String = "",
    @DrawableRes imageRessource: Int,
    rotationImage: Float = 0f,
    modifier: Modifier,
    onClick : () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(2f)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxSize(0.85f)
                .align(Alignment.Center)
                .aspectRatio(2f),
            colors = ButtonColors(
                containerColor = ColorButton,
                contentColor = Color.DarkGray,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.Gray
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {}
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .rotate(rotationImage),
            painter = painterResource(imageRessource),
            contentDescription = contentDescription,
        )
    }
}
