package com.compose.samples

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * MainActivity
 * Created by wangzhen on 2021/6/12.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }

    @Composable
    fun HomeScreen() {
        Scaffold(
            topBar = {
                CreateTopBar()
            },
            drawerContent = {
                CreateDrawer()
            },
            content = {
                CreateContent()
            }
        )
    }

    @Composable
    private fun CreateDrawer() {
        Box(
            modifier = Modifier
                .width(300.dp)
                .fillMaxHeight()
                .background(Color.Yellow)
        )
    }

    @Composable
    private fun CreateTopBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Compose",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }
    }

    @Composable
    private fun CreateContent() {
        val state = rememberScrollState()
        Column(Modifier.verticalScroll(state)) {
            FeedCard()

            Spacer(modifier = Modifier.size(15.dp))

            BoxWeight()

            Spacer(modifier = Modifier.size(15.dp))

            CreateConstraint()

            SelectableText()
        }
    }

    @Composable
    fun SelectableText() {
        Card(elevation = 4.dp, modifier = Modifier.padding(15.dp)) {
            SelectionContainer {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text("This text is selectable")
                    Text("This one too")
                    Text("This one as well")
                    DisableSelection {
                        Text("But not this one", color = Color.Red)
                        Text("Neither this one", color = Color.Red)
                    }
                    Text("But again, you can select this one")
                    Text("And this one too")
                }
            }
        }
    }

    @Composable
    private fun CreateConstraint() {
        Card(
            elevation = 4.dp,
            modifier = Modifier.padding(15.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(15.dp)
            ) {
                val (btn_top_start, text_1, btn_bottom_end, text_2, text_center) = createRefs()
                Button(
                    onClick = {},
                    modifier = Modifier.constrainAs(btn_top_start) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                ) {
                    Text("top start")
                }

                Text("text 1", Modifier.constrainAs(text_1) {
                    top.linkTo(btn_top_start.bottom)
                    start.linkTo(btn_top_start.start)
                })

                Button(
                    onClick = { },
                    modifier = Modifier.constrainAs(btn_bottom_end) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }) {
                    Text("bottom end")
                }

                Text("text 2",
                    modifier = Modifier.constrainAs(text_2) {
                        bottom.linkTo(btn_bottom_end.top)
                        end.linkTo(btn_bottom_end.end)
                    })

                Text(text = "center", modifier = Modifier.constrainAs(text_center) {
                    centerTo(parent)
                })
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
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(
                        shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
                    )
                    .background(Color.Red)
                    .clickable {

                    }
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .fillMaxHeight()
                    .clip(
                        shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
                    )
                    .clickable {

                    }
                    .background(Color.Green)
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