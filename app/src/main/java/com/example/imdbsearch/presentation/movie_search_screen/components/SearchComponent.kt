package com.example.imdbsearch.presentation.movie_search_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.imdbsearch.R
import com.example.imdbsearch.common.Constants
import com.example.imdbsearch.presentation.Screen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchComponent(
    navController: NavController
)
{
    val focusManager = LocalFocusManager.current
    var typeChoice :String? by remember{ mutableStateOf(null) }
    var yearText: String? by remember{ mutableStateOf(null) }
    var isNameBlank by remember {
        mutableStateOf(false)
    }
    var isYearNotValid by remember {
        mutableStateOf(false)
    }
    var searchText by remember{ mutableStateOf(TextFieldValue("")) }
    var isSearchExpanded by remember {
        mutableStateOf(false)
    }
    var isTypeExpanded by remember {
        mutableStateOf(false)
    }
    ConstraintLayout(modifier = Modifier.padding(8.dp)) {
        val (nameTF, dropDownMenu,expandButton,yearTF,searchButton) = createRefs()
        OutlinedTextField(
            modifier=Modifier.constrainAs(nameTF)
            {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
            },
            label = { Text(text = "Movie Name") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone ={
                    focusManager.clearFocus()
                }
            ),
            isError = isNameBlank
        )
        IconButton(
            modifier = Modifier.constrainAs(expandButton)
            {
                start.linkTo(nameTF.end)
                end.linkTo(parent.end)
                top.linkTo(nameTF.top)
                bottom.linkTo(nameTF.bottom)
            },
            onClick = { isSearchExpanded = !isSearchExpanded }
        ) {
            Icon(
                painter = painterResource(id = if (isSearchExpanded) R.drawable.ic_expand_less else R.drawable.ic_expand_more),
                contentDescription = null
            )
        }
        if(isSearchExpanded)
        {
            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.padding(10.dp).constrainAs(dropDownMenu) {
                top.linkTo(nameTF.bottom)
                start.linkTo(nameTF.start)
                end.linkTo(nameTF.end)
            })
            {
                OutlinedTextField(
                    modifier= Modifier
                        .clickable { isTypeExpanded = !isTypeExpanded },
                    enabled = false,
                    value = typeChoice ?: "Choose movie or series",
                    onValueChange = {
                        typeChoice=it
                    },
                    trailingIcon =
                    {
                        Icon(painterResource(id = R.drawable.ic_expand_more),null)
                    }
                )
                DropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth(),
                    expanded = isTypeExpanded,
                    onDismissRequest = { isTypeExpanded = false }) {
                    DropdownMenuItem(onClick = {
                        typeChoice= Constants.MOVIE
                        isTypeExpanded=false
                    }) {
                        Text(text= Constants.MOVIE)
                    }
                    DropdownMenuItem(onClick = {
                        typeChoice= Constants.SERIES
                        isTypeExpanded=false
                    }) {
                        Text(text= Constants.SERIES)
                    }
                }
            }

            OutlinedTextField(
                modifier=Modifier.constrainAs(yearTF)
                {
                    top.linkTo(dropDownMenu.bottom)
                    start.linkTo(dropDownMenu.start)
                    end.linkTo(dropDownMenu.end)
                },
                value = yearText?:"",
                onValueChange = { newText ->
                    yearText = newText
                },
                label = { Text(text = "Year") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone ={
                        focusManager.clearFocus()
                    }
                ),
                isError = isYearNotValid
            )
        }
        Button(modifier = Modifier
            .constrainAs(searchButton){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                if(isSearchExpanded) top.linkTo(yearTF.bottom)
                else  top.linkTo(nameTF.bottom)
            },
            onClick = {
                if(searchText.text=="")
                {
                    isNameBlank=true
                }
                else if(yearText!=null && yearText=="")
                {
                    isYearNotValid=true
                }
                else
                {
                    focusManager.clearFocus()
                    navController.navigate(Screen.MovieSearchScreen.route+"/${searchText.text}/${yearText}/${typeChoice}")
                    isNameBlank=false
                }

            }
        ) {
            Text(modifier = Modifier.align(Alignment.CenterVertically),text = "Search")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchComponentPreview()
{
    SearchComponent(navController = rememberNavController())
}