package com.compose.samples

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * MainActivity
 * Created by wangzhen on 2021/6/12.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                FeedCard()
                Spacer(modifier = Modifier.size(15.dp))
                BoxWeight()
            }
        }
    }

    @Composable
    fun BoxWeight() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 15.dp, end = 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .clip(
                        shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
                    )
                    .background(Color.Red)
                    .clickable {
                        Toast
                            .makeText(this@MainActivity, "weight 2", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(
                        shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
                    )
                    .background(Color.Blue)
                    .clickable {
                        Toast
                            .makeText(this@MainActivity, "weight 1", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }
    }

    @Composable
    fun FeedCard() {
        val style = MaterialTheme.typography
        Column(modifier = Modifier
            .clickable {
                Toast
                    .makeText(this@MainActivity, "click item", Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.header),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(
                    modifier = Modifier.size(10.dp)
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Compose", style = style.h6, color = Color.Black)
                    Text(text = "3 minutes ago", style = style.body2, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(elevation = 4.dp) {
                Image(
                    painter = painterResource(id = R.mipmap.header),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .clickable(onClick = {
                            Toast
                                .makeText(this@MainActivity, "click image", Toast.LENGTH_SHORT)
                                .show()
                        }),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}