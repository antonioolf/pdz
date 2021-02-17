package com.oliveiralabs.pdz.other

import com.google.common.truth.Truth.assertThat
import com.oliveiralabs.pdz.BuildConfig
import org.junit.Test

class BuildConfigTest {

    @Test
    fun `build config test`() {
        assertThat(BuildConfig.DEBUG).isTrue()
        assertThat(BuildConfig.APPLICATION_ID).isNotEmpty()
        assertThat(BuildConfig.BUILD_TYPE).isNotEmpty()
        assertThat(BuildConfig.VERSION_CODE).isNotEqualTo(0)
        assertThat(BuildConfig.VERSION_NAME).isNotEmpty()
    }
}