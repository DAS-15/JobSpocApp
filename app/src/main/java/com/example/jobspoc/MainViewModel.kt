package com.example.jobspoc

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobspoc.models.JobModel
import com.example.jobspoc.ui.theme.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    job: Job
) : ViewModel() {

    private val myJob = job

    private val _IndeedList =
        MutableLiveData(mutableStateOf(mutableStateListOf<JobModel>()))
    val IndeedList: MutableState<SnapshotStateList<JobModel>>? = _IndeedList.value
    fun getIndeedData(
        context: Context,
        userMainRepository: MainRepository,
        job: Job,
        query: String
    ) {
        viewModelScope.launch(job + Dispatchers.Main) {
            viewModelScope.launch(Dispatchers.IO) {
                userMainRepository.getIndeed(context, _IndeedList, query)
            }
        }
    }

    private val _MonsterList=
        MutableLiveData(mutableStateOf(mutableStateListOf<JobModel>()))
    val MonsterList: MutableState<SnapshotStateList<JobModel>>? = _MonsterList.value
    fun getMonsterData(
        context: Context,
        userMainRepository: MainRepository,
        job: Job,
        query: String
    ) {
        viewModelScope.launch(job + Dispatchers.Main) {
            viewModelScope.launch(Dispatchers.IO) {
                userMainRepository.getMonster(context, _MonsterList, query)
            }
        }
    }

    private val _SimplyHiredList=
        MutableLiveData(mutableStateOf(mutableStateListOf<JobModel>()))
    val SimplyHiredList: MutableState<SnapshotStateList<JobModel>>? = _SimplyHiredList.value
    fun getSimplyHiredData(
        context: Context,
        userMainRepository: MainRepository,
        job: Job,
        query: String
    ) {
        viewModelScope.launch(job + Dispatchers.Main) {
            viewModelScope.launch(Dispatchers.IO) {
                userMainRepository.getSimplyHired(context, _SimplyHiredList, query)
            }
        }
    }

    public override fun onCleared() {
        super.onCleared()
        myJob.cancel()
    }

}