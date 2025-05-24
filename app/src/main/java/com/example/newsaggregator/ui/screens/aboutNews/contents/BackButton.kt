package com.example.newsaggregator.ui.screens.aboutNews.contents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsaggregator.R


@Composable
fun BackButton(navController: NavController){
    Box(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(30.dp),
    ){
       IconButton(
           onClick = {
               navController.popBackStack()
           },
       ) {
           Image(
               painter = painterResource(R.drawable.back),
               contentDescription = "back button",
               modifier = Modifier
                   .height(10.dp)
           )
       }
    }
}