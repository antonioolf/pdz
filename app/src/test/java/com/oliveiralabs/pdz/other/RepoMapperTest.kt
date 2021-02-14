package com.oliveiralabs.pdz.other

import com.google.common.truth.Truth.assertThat
import com.oliveiralabs.pdz.activities.MainActivity
import com.oliveiralabs.pdz.models.Formula
import com.oliveiralabs.pdz.others.RepoMapper
import org.junit.Test
import org.robolectric.Robolectric


class RepoMapperTest {
    // val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun `empty response gives an empty mapping`() {
        RepoMapper.updateMapping("")
        val repoMap :MutableMap<String, List<Formula>> = RepoMapper.getMapping()
        assertThat(repoMap).isEmpty()
    }

    @Test
    fun `valid response gives a valid mapping`() {
        /*
        val bufferedReader: BufferedReader = File("assets/response.json").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }

        RepoMapper.updateMapping("")
        val repoMap :MutableMap<String, List<Formula>> = RepoMapper.getMapping()
        assertThat(repoMap).isEmpty()*/
    }
}