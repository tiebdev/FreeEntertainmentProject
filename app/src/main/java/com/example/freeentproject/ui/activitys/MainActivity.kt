package com.example.freeentproject.ui.activitys
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.freeentproject.R
import com.example.freeentproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/*
Esta es clase main y por lo tanto la clase principal, es recomendable no tenga mucho contenido en su
método onCreate, así la aplicación va más fluida y consume menos recursos. Lo que hacemos es viajar
directamente al InicioFragment, descargando asi esta clase ante la carga de llamadas iniciales a la
bd. Utilizamos para ello el navHostFragment y el navController, que son elementos de navegación
entre Fragments.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment = supportFragmentManager
                                        .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navigationRail.setupWithNavController(navController)
        setContentView(binding.root)
    }
}