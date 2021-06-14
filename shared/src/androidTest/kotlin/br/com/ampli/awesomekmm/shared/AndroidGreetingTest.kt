package br.com.ampli.awesomekmm.shared

import br.com.ampli.awesomekmm.Greeting
import junit.framework.Assert.assertTrue
import org.junit.Test

class AndroidGreetingText {

    @Test
    fun testExam() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))
    }
}