package com.example.rowapp.service

import com.example.rowapp.App
import com.example.rowapp.dataClasses.Game
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.rowapp.R
import com.example.rowapp.dataClasses.GameState
import com.google.gson.Gson
import org.json.JSONObject

typealias GameServiceCallback = (state: Game?, errorCode: Int?) -> Unit

object GameService {

    private val context = App.context

    // use a queue for performing requests.
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    private enum class APIEndpoints(val url: String) {
        CREATE_GAME(
            "%1s%2s%3s".format(
                context.getString(R.string.protocol),
                context.getString(R.string.domain),
                context.getString(R.string.base_path)
            )
        )
    }

    // Function to create a new game
    fun createGame(playerId: String, state: GameState, callback: GameServiceCallback) {

        var url = APIEndpoints.CREATE_GAME.url

        val requestData = JSONObject()
        requestData.put("player", playerId)
        requestData.put("State", state)

        val request = object : JsonObjectRequest(Request.Method.POST, url, requestData,
            {
                // Sucsess: Game created
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                callback(game, null)
            }, {
                // Error: Game not created
                callback(null, it.networkResponse.statusCode)
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQueue.add(request)
    }

    // Function to join a game
    fun joinGame() {

    }

    // Function to update the game
    fun updateGame() {

    }

    // function to poll the game. The game does not automaticly update, so it will check for updates approx every 5sec.
    fun pollGame() {

    }
}