package com.example.newsaggregator.ui.screens.newsHome


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsaggregator.domain.Item
import com.example.newsaggregator.ui.Loading
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun NewsHomeScreen(
    viewModel: NewsHomeViewModel = hiltViewModel(),
    navController: NavController,
){
    val state = viewModel.newsHomeState.collectAsState()

    if(state.value.isLoading == true) {
        Loading()
    }
    else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(state.value.items) { item ->
                NewsCard(item, navController)
            }
        }
    }
}

@Composable
fun NewsCard(item: Item, navController: NavController){
    Card(
        modifier = Modifier
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            val encodedUrl = URLEncoder.encode(item.guid, StandardCharsets.UTF_8.toString())
            navController.navigate("about_news_screen/$encodedUrl")
        }
    ) {
        Column{
            AsyncImage(
                model = item.contents.get(1).url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )

            Box(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )
                    Text(
                        text = AnnotatedString.fromHtml(item.description).toString(),
                        modifier = Modifier
                            .padding(top = 6.dp),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Box(
                        contentAlignment = Alignment.BottomEnd,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = item.dcCreator + " : " + item.pubDate,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}