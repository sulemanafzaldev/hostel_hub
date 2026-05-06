package com.bkuc.hostelhub.utils

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

object StatusBar {
    fun changeStatusBarColor(activity: Activity, @ColorRes colorRes: Int, lightIcons: Boolean = false) {
        val window = activity.window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM){
              window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
                view.setBackgroundColor(activity.resources.getColor(colorRes,activity.theme))

                // Adjust padding to avoid overlap
                view.setPadding(0, statusBarInsets.top, 0, 0)
                insets
            }
        }
        else{
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            // ✅ Set status bar color
            window.statusBarColor = ContextCompat.getColor(activity, colorRes)

            // ✅ Ensure layout does NOT extend behind status bar
            WindowCompat.setDecorFitsSystemWindows(window, true)

            // ✅ Set status bar icon color (light/dark)
            val controller = WindowInsetsControllerCompat(window, window.decorView)
            controller.isAppearanceLightStatusBars = lightIcons
        }
        // Required for drawing system bar backgrounds

    }
}


