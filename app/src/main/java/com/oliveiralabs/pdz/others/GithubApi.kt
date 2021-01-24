package com.oliveiralabs.pdz.others

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class GithubApi(context: Context, listener: RequestCallback) {
    private var ctx: Context = context
    private var requestListener: RequestCallback = listener

    private var queue: RequestQueue
    private val baseUrl :String = "https://api.github.com"

    interface RequestCallback {
        fun getAllFilesResponse(response :String)
        fun getRepoContentResponse(response :String)
    }

    init {
        queue = Volley.newRequestQueue(ctx)
    }

    fun getAllFiles(userRepo :String, branch :String) {

        val stringRequest = StringRequest(Request.Method.GET, "$baseUrl/repos/$userRepo/git/trees/${branch}?recursive=1", { response ->
            requestListener.getAllFilesResponse(response.toString())
        }, { err ->
            Toast.makeText(ctx, err.toString(), Toast.LENGTH_SHORT).show()
            requestListener.getAllFilesResponse("[]")
        })

        queue.add(stringRequest)
    }

    fun getRepoContent(userRepo :String, path :String) {

        val stringRequest = StringRequest(Request.Method.GET, "$baseUrl/repos/$userRepo/contents/${path}", { response ->
            requestListener.getRepoContentResponse(response.toString())
        }, { err ->
            requestListener.getRepoContentResponse(err.toString())
        })

        queue.add(stringRequest)
    }

}