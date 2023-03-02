package com.example.jobspoc

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobspoc.ui.theme.JobSpocTheme
import com.example.jobspoc.ui.theme.MainRepository
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobSpocTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var mytext = remember {
                        mutableStateOf("python developer")
                    }

                    var login_status = remember {
                        mutableStateOf(false)
                    }

//                    val job = Job()
//                    val vm = MainViewModel(job)
//
//                    Login(this, login_status, mytext, job, vm)

                    val job = Job()
                    val vm = MainViewModel(job)
                    vm.getIndeedData(this, MainRepository(), job, mytext.value)
                    vm.getMonsterData(this, MainRepository(), job, mytext.value)
                    vm.getSimplyHiredData(this, MainRepository(), job, mytext.value)
                    MyApp(this, vm, job, mytext)
                }
            }
        }
    }
}

@Composable
fun MyApp(
    context: Context,
    vm: MainViewModel,
    job: CompletableJob,
    mytext: MutableState<String>
) {

    var searchtext by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            Column() {
                Text(
                    text = "Job Spoc",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier.background(MaterialTheme.colors.onPrimary)
                ) {

//                    val textfieldstatus = remember {
//                        mutableStateOf(true)
//                    }

//                    if(vm.IndeedList?.value?.isNotEmpty() == true) {
////                        textfieldstatus.value = true
//                    }

                    CompositionLocalProvider(LocalContentColor provides Color.White) {
                        TextField(
                            value = searchtext,
                            onValueChange = {
                                searchtext = it
                            },
                            modifier = Modifier
                                .height(55.dp)
                                .fillMaxWidth()
                                .background(Color.DarkGray),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search",
                                    modifier = Modifier.clickable {
                                        mytext.value = searchtext
                                        searchtext = ""
//                                    textfieldstatus.value = false
                                    })
                            },
                            placeholder = {
                                Text(text = "Search", color = Color.Gray.copy(alpha = 0.5f))
                            },
                            enabled = true,
                            maxLines = 1
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.DarkGray
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {

            LazyColumn {

                if (vm.IndeedList?.value?.isNotEmpty() == true) {
                    items(vm.IndeedList.value.size) { i ->
                        JobItem(
                            jobTitle = vm.IndeedList.value[i].jobtitle,
                            companyName = vm.IndeedList.value[i].companyname,
                            companyLocation = vm.IndeedList.value[i].companylocation,
                            urlLink = vm.IndeedList.value[i].urllink,
                        )
                    }
//                if(IndeedList?.value?.size!! > 0){
//                    Text(text = IndeedList.value[0].toString(), color = Color.Red)
//                }
                }
                if (vm.MonsterList?.value?.isNotEmpty() == true) {
                    items(vm.MonsterList.value.size) { i ->
                        JobItem(
                            jobTitle = vm.MonsterList.value[i].jobtitle,
                            companyName = vm.MonsterList.value[i].companyname,
                            companyLocation = vm.MonsterList.value[i].companylocation,
                            urlLink = vm.MonsterList.value[i].urllink,
                        )
                    }
//                if(IndeedList?.value?.size!! > 0){
//                    Text(text = IndeedList.value[0].toString(), color = Color.Red)
//                }
                }
                if (vm.SimplyHiredList?.value?.isNotEmpty() == true) {
                    items(vm.SimplyHiredList.value.size) { i ->
                        JobItem(
                            jobTitle = vm.SimplyHiredList.value[i].jobtitle,
                            companyName = vm.SimplyHiredList.value[i].companyname,
                            companyLocation = vm.SimplyHiredList.value[i].companylocation,
                            urlLink = vm.SimplyHiredList.value[i].urllink,
                        )
                    }
//                if(IndeedList?.value?.size!! > 0){
//                    Text(text = IndeedList.value[0].toString(), color = Color.Red)
//                }
                }
            }
        }
    }
}

@Composable
fun JobItem(
    jobTitle: String = "Android Developer",
    companyName: String = "Google",
    companyLocation: String = "Pune",
    urlLink: String = "https://www.google.com/",
    websiteName: String = "LinkedIn"
) {
    val uriHandler = LocalUriHandler.current
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                uriHandler.openUri("https:$urlLink")
            }
            .padding(top = 2.5.dp, bottom = 2.5.dp),
        backgroundColor = MaterialTheme.colors.onSecondary
    ) {
        Column() {
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            Text(
                text = jobTitle,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 10.dp),
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(bottom = 2.dp))
            Text(
                text = companyName,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.padding(bottom = 2.dp))
            Text(
                text = companyLocation,
                modifier = Modifier.padding(start = 10.dp),
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}
