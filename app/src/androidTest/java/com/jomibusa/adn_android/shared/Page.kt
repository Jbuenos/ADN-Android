package com.jomibusa.adn_android.shared


import android.os.SystemClock
import androidx.test.espresso.Espresso

open class Page {

    inline fun <reified T : Page> on(): T {
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    open fun verify(): Page {
        assert(false)
        return this
    }

    fun back(): Page {
        Espresso.pressBack()
        return this
    }

    fun wait(milliseconds: Long): Page {
        SystemClock.sleep(milliseconds)
        return this
    }

    companion object {
        inline fun <reified T : Page> on(): T {
            return Page().on()
        }
    }

}