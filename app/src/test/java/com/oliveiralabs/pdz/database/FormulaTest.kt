package com.oliveiralabs.pdz.database

import com.google.common.truth.Truth
import com.oliveiralabs.pdz.models.Formula
import org.junit.Test

class FormulaTest {

    @Test
    fun `test formula model`() {
        val formula = Formula(
            "rit aws create bucket",
            "https://raw.githubusercontent.com/ZupIT/ritchie-formulas/master/aws/create/bucket/README.md",
            "https://raw.githubusercontent.com/ZupIT/ritchie-formulas/master/aws/create/bucket/help.json"
        )

        Truth.assertThat(formula).isNotNull()
        Truth.assertThat(formula.command).isNotNull()
        Truth.assertThat(formula.readmeURL).isNotNull()
        Truth.assertThat(formula.helpJsonURL).isNotNull()
    }
}