package dev.pegasus.whatsapp.message.recovery.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import dev.pegasus.whatsapp.message.recovery.App
import dev.pegasus.whatsapp.message.recovery.R
import dev.pegasus.whatsapp.message.recovery.databinding.ActivityMainBinding
import dev.pegasus.whatsapp.message.recovery.presentation.viewModels.ViewModelDbMessages
import dev.pegasus.whatsapp.message.recovery.presentation.viewModels.ViewModelDbProvider

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ViewModelDbMessages> { ViewModelDbProvider(App.Companion.diManual.useCase) }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        setUI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreen()
        setUI()

        binding.mbGrantPermissionMain.setOnClickListener { askPermission() }
    }

    private fun fullScreen() {
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUI() {
        binding.llPermissionMain.isVisible = !checkPermission()
    }

    private fun checkPermission(): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(this).contains(packageName)
    }

    private fun askPermission() {
        val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        permissionLauncher.launch(intent)
    }
}