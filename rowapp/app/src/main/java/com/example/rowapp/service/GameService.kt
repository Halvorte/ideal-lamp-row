package com.example.rowapp.service

import com.example.rowapp.dataClasses.Game
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.rowapp.App
import com.example.rowapp.R
import com.example.rowapp.dataClasses.GameState
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

typealias GameServiceCallback = (state: Game?, errorCode: Int?) -> Unit

object GameService {


    val context = App.context

    // use a queue for performing requests.
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    var currentGameId: String? = null

    var joinGameId: String? = null

    private enum class APIEndpoints(val url: String) {
        CREATE_GAME(
            "%1s%2s%3s".format(
                context.getString(R.string.protocol),
                context.getString(R.string.domain),
                context.getString(R.string.base_path)
            )
        ),
        JOIN_GAME(
            "%1s%2s%3s%4s".format(
                context.getString(R.string.protocol),
                context.getString(R.string.domain),
                context.getString(R.string.base_path),
                context.getString(R.string.join_game_path).format(joinGameId)
            )
        ),
        POLL_GAME(
            "%1s%2s%3s%4s".format(
                context.getString(R.string.protocol),
                context.getString(R.string.domain),
                context.getString(R.string.base_path),
                context.getString(R.string.poll_game_path).format(currentGameId)
            )
        ),
        UPDATE_GAME(
            "%1s%2s%3s%4s".format(
                context.getString(R.string.protocol),
                context.getString(R.string.domain),
                context.getString(R.string.base_path),
                context.getString(R.string.update_game_path).format(currentGameId)
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
                println(it.toString(3))
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

    fun joinGame(playerId: String, gameId: String, callback: GameServiceCallback) {

        currentGameId = gameId.toString()

        joinGameId = gameId.toString()

        //var url = "/Game/$currentGameId/join"
        var url = APIEndpoints.JOIN_GAME.url

        var url2 = "https://generic-game-service.herokuapp.com/Game/$gameId/join"


        val requestData = JSONObject()
        requestData.put("player", playerId)
        requestData.put("gameId", gameId)

        val request = object : JsonObjectRequest(Request.Method.POST, url2, requestData,
            {
                // Success game joined.
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                callback(game, null)
                println(it.toString(3))
            }, {
                // Error joining new game.
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

    fun updateGame(gameId: String, gameState: GameState, callback: GameServiceCallback) {

        currentGameId = gameId

        var players: List<String>

        var url = APIEndpoints.UPDATE_GAME.url
        var url3 = "https://generic-game-service.herokuapp.com/Game/$gameId/update"

        val requestData = JSONObject()
        requestData.put("gameId", gameId)
        requestData.put("state", JSONArray(gameState))

        val request = object : JsonObjectRequest(Request.Method.POST, url, requestData,
            {
                // Success updating game.
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                callback(game, null)
                println(it.toString(3))
            }, {
                // Error updating game.
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

    fun pollGame(gameId: String, callback: GameServiceCallback) {

        currentGameId = gameId

        var url = APIEndpoints.POLL_GAME.url
        var url4 = "https://generic-game-service.herokuapp.com/Game/$gameId/poll"

        val requestData = JSONObject()
        requestData.put("gameId", gameId)

        val request = object : JsonObjectRequest(Request.Method.GET, url4, requestData,
            {
                // Success game polled.
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                callback(game, null)
                println(it.toString(3))
            }, {
                // Error creating polling game.
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
}