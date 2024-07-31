package com.example.gridapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gridapplication.data.DataSource
import com.example.gridapplication.ui.theme.GridApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GridApplication(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GridCard(
    @StringRes stringResourceId: Int,
    likes: Int,
    @DrawableRes imageResourceId: Int
) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.spacedBy(16.dp),


    ) {
        Box {
            Image(
                painter = painterResource(id = imageResourceId), contentDescription = stringResource(
                    id = stringResourceId,
                ),
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Crop
            )
        }



        Column(modifier = Modifier) {
            Text(text = stringResource(id = stringResourceId), fontWeight = FontWeight.SemiBold)

            Row (
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_grain),
                    contentDescription = "Likes"
                )
                Text(text = likes.toString())
            }


        }

    }
}


@Composable
fun GridApplication(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.statusBarsPadding().navigationBarsPadding(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        content = {
            items(DataSource().loadTopics()) { topic ->
                GridCard(
                    stringResourceId = topic.stringResourceId,
                    likes = topic.likes,
                    imageResourceId = topic.imageResourceId
                )
            }
        })

}




