package com.compose.samples

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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

            CreateAnimation()

            CreateConstraint()

            SelectableText()

            TextFields()
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun CreateAnimation() {
        Card(elevation = 4.dp, modifier = Modifier.padding(15.dp)) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "AnimatedVisibility")
                Row {
                    var editable by remember { mutableStateOf(true) }
                    Button(onClick = {
                        editable = !editable
                    }) {
                        Text(text = "open/close")
                    }
                    AnimatedVisibility(
                        visible = editable,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally()
                    ) {
                        Button(
                            onClick = {
                                Toast.makeText(this@MainActivity, "Edit", Toast.LENGTH_SHORT).show()
                            }, modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(text = "Edit")
                        }
                    }
                }

                Text(text = "animateContentSize")
                var message by remember { mutableStateOf("Hello") }
                Button(onClick = {
                    message += "\n${System.currentTimeMillis()}"
                }) {
                    Text(text = "add text")
                }
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .background(Color(0xFF007C7C))
                        .animateContentSize()
                ) {
                    Text(text = message, color = Color.White)
                }
            }
        }
    }

    @Composable
    private fun TextFields() {
        Card(elevation = 4.dp, modifier = Modifier.padding(15.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {

                /**
                 * by remember 报错：
                 * Type 'Type Variable(T)' has no method 'getValue(Nothing?, KProperty<*>)' and thus it cannot serve as a delegate
                 * 解决方法：import androidx.compose.runtime.getValue
                 */
                var text by remember { mutableStateOf("") }

                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    label = {
                        Text("Label")
                    },
                    placeholder = {
                        Text("TextField")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                var textOutLined by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = textOutLined,
                    onValueChange = {
                        textOutLined = it
                    },
                    label = {
                        Text("Label")
                    },
                    placeholder = {
                        Text("OutlinedTextField")
                    },
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                var password by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = {
                        Text("Enter Password")
                    },
                    placeholder = {
                        Text("Enter Password")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
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
                val (btn_canvas, btn_bottom_end, text_center) = createRefs()
                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, CanvasActivity::class.java))
                    },
                    modifier = Modifier.constrainAs(btn_canvas) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                ) {
                    Text("canvas")
                }

                Button(
                    onClick = { },
                    modifier = Modifier.constrainAs(btn_bottom_end) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }) {
                    Text("bottom end")
                }

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