package com.example.notas_trabalho

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withHint


import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.notas_trabalho", appContext.packageName)

        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        activityScenario.moveToState(Lifecycle.State.RESUMED)

        /**
         *
         *  Na Fragment area janela de Login
         *
         */


        onView(withId(R.id.textView6)).check(matches(isDisplayed()))
        onView(withId(R.id.textView6)).check(matches(withText("LOGIN")))

        onView(withId(R.id.textView4)).check(matches(isDisplayed()))
        onView(withId(R.id.textView4)).check(matches(withText("Email")))

        onView(withId(R.id.inputNome)).check(matches(isDisplayed()))
        onView(withId(R.id.inputNome)).check(matches(withHint("Digite seu email")))

        onView(withId(R.id.textView5)).check(matches(isDisplayed()))
        onView(withId(R.id.textView5)).check(matches(withText("Senha")))

        onView(withId(R.id.inputSobrenome)).check(matches(isDisplayed()))
        onView(withId(R.id.inputSobrenome)).check(matches(withHint("Digite sua senha")))

        onView(withId(R.id.buttonProximo)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonProximo)).check(matches(withText("Avançar")))

        onView(withId(R.id.btnRegistrar)).check(matches(isDisplayed()))
        onView(withId(R.id.btnRegistrar)).check(matches(withText("Registrar")))
        onView(withId(R.id.btnRegistrar)).perform(click())

        /**
         *
         *  Na Fragment de Cadastro do usuario
         *
         */

        onView(withId(R.id.textView2)).check(matches(isDisplayed()))
        onView(withId(R.id.textView2)).check(matches(withText("CADASTRO")))

        onView(withId(R.id.textView3)).check(matches(isDisplayed()))
        onView(withId(R.id.textView3)).check(matches(withText("Email")))

        onView(withId(R.id.textInputEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.textInputEmail)).check(matches(withHint("Digite seu Email")))

        onView(withId(R.id.textView)).check(matches(isDisplayed()))
        onView(withId(R.id.textView)).check(matches(withText("Senha")))

        onView(withId(R.id.inputSenha)).check(matches(isDisplayed()))
        onView(withId(R.id.inputSenha)).check(matches(withHint("Digite sua Senha")))

        onView(withId(R.id.prxBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.prxBtn)).check(matches(withText("Avançar")))


        // typeTexts nos inputs

        onView(withId(R.id.textInputEmail)).perform(
            typeText("Thiago@teste.com"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.textInputEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.textInputEmail)).check(matches(withText("Thiago@teste.com")))

        onView(withId(R.id.inputSenha)).perform(
            typeText("Dias123"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.inputSenha)).check(matches(isDisplayed()))
        onView(withId(R.id.inputSenha)).check(matches(withText("Dias123")))

        onView(withId(R.id.prxBtn)).perform(click())

        /**
         *
         *  Fragment Login novamente
         *
         */
        Thread.sleep(2000)

        onView(withId(R.id.inputNome)).perform(
            typeText("Thiago@teste.com"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.inputNome)).check(matches(isDisplayed()))
        onView(withId(R.id.inputNome)).check(matches(withText("Thiago@teste.com")))

        onView(withId(R.id.inputSobrenome)).perform(
            typeText("Dias123"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.inputSobrenome)).check(matches(isDisplayed()))
        onView(withId(R.id.inputSobrenome)).check(matches(withText("Dias123")))

        onView(withId(R.id.buttonProximo)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.textInputEditText))
            .perform(typeText("Minha nota de teste"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.save_button)).perform(click())

        onView(withId(R.id.textInputEditText))
            .check(matches(withText("Minha nota de teste")))

        onView(withId(R.id.save_button)).perform(click())

        onView(withId(R.id.delete_button)).perform(click())

        onView(withId(R.id.singOut)).perform(click())

        onView(withId(R.id.buttonProximo))
            .check(matches(isDisplayed()))

        onView(withId(R.id.buttonProximo))
            .check(matches(isDisplayed()))
    }
}
