package com.fabirt.roka.features

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fabirt.roka.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        configureStatusBarForFullscreen()
    }

    private fun configureStatusBarForFullscreen() {
        var flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }

        window.decorView.systemUiVisibility = flags
    }

    /*
    Dynamic start destination
    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.mainNavHostFragment)
        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)
        lifecycleScope.launch {
            val didShow = dataStoreViewModel.readOnboardingDidShow()
            var startDestination = R.id.onboardingFragment
            if (didShow) {
                startDestination = R.id.homeFragment
            }
            navGraph.startDestination = startDestination
            navController.graph = navGraph
        }
    }

     */
}