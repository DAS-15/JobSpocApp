package com.example.jobspoc.ui.theme

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.jobspoc.models.JobModel
import com.google.gson.Gson

class MainRepository() {
    suspend fun getIndeed(
        context: Context,
        _IndeedList: MutableLiveData<MutableState<SnapshotStateList<JobModel>>>,
        end_point: String
    ): MutableLiveData<MutableState<SnapshotStateList<JobModel>>> {

        _IndeedList.value?.value?.clear()

        val formatted_end_point = end_point.split(" ").toTypedArray()
        var new_end_point = ""
        for (i in formatted_end_point) {
            new_end_point += "$i%20"
        }

        val gson = Gson()
        val url = "https://job-spoc-api.herokuapp.com/indeed/$new_end_point"
        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                Toast.makeText(context, "Fetched data successfully", Toast.LENGTH_LONG).show()
                Toast.makeText(
                    context,
                    "https://job-spoc-api.herokuapp.com/indeed/$new_end_point",
                    Toast.LENGTH_LONG
                ).show()
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()
                for (i in 0 until response.length()) {
                    _IndeedList.value?.value?.add(
                        gson.fromJson(
                            response.getJSONObject(i).toString(),
                            JobModel::class.java
                        )
                    )
                }

//                    Toast.makeText(
//                        context,
//                        response[i].toString(),
////                        Toast.LENGTH_LONG
////                    ).show()
//                }
            }, {
                Toast.makeText(context, "Unable to fetch data!!!", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

        return _IndeedList
    }


    suspend fun getMonster(
        context: Context,
        _MonsterList: MutableLiveData<MutableState<SnapshotStateList<JobModel>>>,
        end_point: String
    ): MutableLiveData<MutableState<SnapshotStateList<JobModel>>> {

        _MonsterList.value?.value?.clear()

        val formatted_end_point = end_point.split(" ").toTypedArray()
        var new_end_point = ""
        for (i in formatted_end_point) {
            new_end_point += "$i%20"
        }

        val gson = Gson()
        val url = "https://job-spoc-api.herokuapp.com/monster/$new_end_point"
        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                Toast.makeText(context, "Fetched data successfully", Toast.LENGTH_LONG).show()
                Toast.makeText(
                    context,
                    "https://job-spoc-api.herokuapp.com/monster/$new_end_point",
                    Toast.LENGTH_LONG
                ).show()
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()
                for (i in 0 until response.length()) {
                    _MonsterList.value?.value?.add(
                        gson.fromJson(
                            response.getJSONObject(i).toString(),
                            JobModel::class.java
                        )
                    )
                }

//                    Toast.makeText(
//                        context,
//                        response[i].toString(),
////                        Toast.LENGTH_LONG
////                    ).show()
//                }
            }, {
                Toast.makeText(context, "Unable to fetch data!!!", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

        return _MonsterList
    }

    suspend fun getSimplyHired(
        context: Context,
        _MonsterList: MutableLiveData<MutableState<SnapshotStateList<JobModel>>>,
        end_point: String
    ): MutableLiveData<MutableState<SnapshotStateList<JobModel>>> {

        _MonsterList.value?.value?.clear()

        val formatted_end_point = end_point.split(" ").toTypedArray()
        var new_end_point = ""
        for (i in formatted_end_point) {
            new_end_point += "$i%20"
        }

        val gson = Gson()
        val url = "https://job-spoc-api.herokuapp.com/simplyhired/$new_end_point"
        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                Toast.makeText(context, "Fetched data successfully", Toast.LENGTH_LONG).show()
                Toast.makeText(
                    context,
                    "https://job-spoc-api.herokuapp.com/simplyhired/$new_end_point",
                    Toast.LENGTH_LONG
                ).show()
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()
                for (i in 0 until response.length()) {
                    _MonsterList.value?.value?.add(
                        gson.fromJson(
                            response.getJSONObject(i).toString(),
                            JobModel::class.java
                        )
                    )
                }

//                    Toast.makeText(
//                        context,
//                        response[i].toString(),
////                        Toast.LENGTH_LONG
////                    ).show()
//                }
            }, {
                Toast.makeText(context, "Unable to fetch data!!!", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

        return _MonsterList
    }

}