package com.example.newsaggregator.ui.screens.aboutNews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsaggregator.ui.screens.aboutNews.contents.BackButton
import java.util.regex.Pattern


@Composable
fun AboutNewsScreen(
    viewModel: AboutNewsViewModel = hiltViewModel(),
    navController: NavController,
){
    val state = viewModel.aboutNewsState.collectAsState()
    val item = state.value.item

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BackButton(navController)
            Text(
                text = item?.title ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 5.dp)
            )
            AsyncImage(
                model = item?.contents?.get(1)?.url ?: "",
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .height(300.dp)

            )
            Text(
                text = AnnotatedString.fromHtml(item?.description ?: ""),
                style = TextStyle(
                    fontSize = 16.sp,
                ),
                modifier = Modifier
                    .padding(top = 5.dp)
            )
            Button(
                onClick = {
                    val desc = item?.description
                    val continueReadingUrl = extractContinueReadingUrl(desc)
                    continueReadingUrl?.let { url ->
                        openUrl(context, url)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(bottom = 30.dp)
            ){
                Text(
                    text = "Continue reading",
                    fontSize = 16.sp
                )
            }
        }
    }
}

fun extractContinueReadingUrl(html: String?): String? {
    if (html.isNullOrEmpty()) return null

    val pattern = Pattern.compile("<a href=\"([^\"]*)\">Continue reading...</a>")
    val matcher = pattern.matcher(html)

    return if (matcher.find()) {
        matcher.group(1)
    } else {
        null
    }
}

fun openUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("!!!error", "Ошибка перехода по URL: $url", e)
    }
}