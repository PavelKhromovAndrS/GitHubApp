package ru.pavelkhromov.githubapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment

    }

}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App