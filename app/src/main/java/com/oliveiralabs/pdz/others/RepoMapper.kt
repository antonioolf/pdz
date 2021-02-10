package com.oliveiralabs.pdz.others

import com.oliveiralabs.pdz.models.Formula
import org.json.JSONArray
import org.json.JSONObject

class RepoMapper {
    companion object {
        private lateinit var mapping : MutableMap<String, List<Formula>>

        fun getMapping(): MutableMap<String, List<Formula>> = mapping

        private val fileTypes = mapOf(
                "file"                  to  "100644",
                "file-group-writeable"  to  "100664",
                "executable"            to  "100755"
        )

        fun updateMapping(response: String) {
            val result :MutableMap<String, List<Formula>> = mutableMapOf()

            val jsonObj = JSONObject(response)
            val jsonArray = jsonObj.getJSONArray("tree")

            val groupsDuplicated = arrayListOf<String>()
            val formulasDuplicated = arrayListOf<String>()

            for (i in 0 until jsonArray.length()) {
                val item : JSONObject = jsonArray.get(i) as JSONObject
                val mode :String = item.get("mode") as String
                val path = item.get("path") as String

                if (fileTypes.containsValue(mode) && path.contains("/src/")) {
                    val root = path.split("/")[0]
                    groupsDuplicated.add(root)

                    val beforeSrc = path.split("/src/")[0]
                    formulasDuplicated.add(beforeSrc)
                }
            }

            val groups = groupsDuplicated.distinct()
            val formulas = formulasDuplicated.distinct()

            for (g in groups) {

                val groupFormulas :List<Formula> = formulas
                        .filter { it.startsWith("${g}/") }
                        .map {

                            val helpJsonURL = getURLByFileName(it, jsonArray, "help.json")
                            val readmeURL = getURLByFileName(it, jsonArray, "README.md")
                            Formula(it, readmeURL, helpJsonURL)
                        }

                result[g] = groupFormulas
            }

            mapping = result
        }

        private fun getURLByFileName(command :String, jsonArray: JSONArray, fileName: String): String {
            var result = ""

            for (i in 0 until jsonArray.length()) {
                val item : JSONObject = jsonArray.get(i) as JSONObject
                val path = item.get("path") as String

                if (path == "${command}/${fileName}") {
                    result = item.get("url") as String
                    break
                }
            }

            return result
        }
    }
}