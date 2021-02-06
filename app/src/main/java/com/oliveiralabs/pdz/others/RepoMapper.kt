package com.oliveiralabs.pdz.others

import com.oliveiralabs.pdz.models.Formula
import org.json.JSONObject

class RepoMapper {
    private val fileTypes = mapOf(
            "file"                  to  "100644",
            "file-group-writeable"  to  "100664",
            "executable"            to  "100755"
    )

    fun getRepoItemsMap(response: String): MutableMap<String, List<Formula>> {
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
                        val readme = getReadme(it)
                        val shortDescription = getShortDescription(it)
                        Formula(it, readme, shortDescription)

                    }

            result[g] = groupFormulas
        }

        return result
    }

    private fun getShortDescription(path: String): String {
        return "sample Short Description"
    }

    private fun getReadme(path: String): String {
        return "sample readme"
    }

}