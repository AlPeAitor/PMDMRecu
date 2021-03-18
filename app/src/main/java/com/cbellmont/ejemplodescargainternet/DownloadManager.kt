package com.cbellmont.ejemplodescargainternet

import com.cbellmont.ejemplodescargainternet.People
import com.cbellmont.ejemplodescargainternet.Especie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class
DownloaderManager {
    companion object{
        suspend fun downloadAllPeople(): List<People>? {
            val client = OkHttpClient()
            val url = "https://swapi.dev/api/people/"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)

            val gson = Gson()
            val itemType = object : TypeToken<List<People>>(){}.type
            return gson.fromJson<List<People>>(call.execute().body?.string(),itemType)
        }

        suspend fun downloadEspecie():Especie?{
            val client = OkHttpClient()
            val url = "https://swapi.dev/api/species/"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            val gson = Gson()
            val itemType = object : TypeToken<Especie>(){}.type

            return gson.fromJson(call.execute().body?.string(),itemType)
        }
        suspend fun transformJsonToGson(jsonData :String): List<People>? {
            val JsonObject = JSONArray(jsonData)
            println(JsonObject)
            val gson = Gson()
            val itemType = object : TypeToken<List<People>>(){}.type
            return gson.fromJson<List<People>>(jsonData.toString(), itemType)
        }


    }
}