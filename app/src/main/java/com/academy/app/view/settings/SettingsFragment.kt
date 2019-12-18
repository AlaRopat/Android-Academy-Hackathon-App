package com.academy.hackathonapp.view.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.academy.app.dependency.DataStorage
import com.academy.app.view.MainActivity
import com.academy.app.view.MainFragment
import com.ammarptn.gdriverest.DriveServiceHelper
import com.ammarptn.gdriverest.DriveServiceHelper.getGoogleDriveService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.gson.Gson
import java.io.File

class SettingsFragment : Fragment() {

    companion object {
        private val APP_NAME = "financialAssistant"
    }

    private lateinit var viewModel: SettingsViewModel
    private lateinit var driveServiceHelper: DriveServiceHelper
    private lateinit var folderId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View(context!!.applicationContext)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            SettingsViewModel.SettingsViewModelFactory(
                DataStorage.categoryRepository,
                DataStorage.expenseRepository
            )
        ).get(SettingsViewModel::class.java)

        viewModel.categoryList.observe(this, Observer {
            uploadFile(it.first)
            uploadFile(it.second)
        })
    }

    private fun uploadFile(file: File) {
        driveServiceHelper.uploadFile(file, "text/plain", folderId)
            .addOnSuccessListener { googleDriveFileHolder ->
                val gson = Gson()
                Log.d("SettingsFragment", "onSuccess: " + gson.toJson(googleDriveFileHolder))
            }
            .addOnFailureListener { e -> Log.d("SettingsFragment", "onFailure: " + e.message) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val account = GoogleSignIn.getLastSignedInAccount(activity)
        if (account == null) {
            Toast.makeText(activity, "You didn't sign in", Toast.LENGTH_LONG).show()
            val mainActivity = activity as MainActivity
            mainActivity.showFragment(MainFragment(), false)
            return
        }

        val googleDriveService = getGoogleDriveService(activity, account, APP_NAME)
        driveServiceHelper = DriveServiceHelper(googleDriveService)

        driveServiceHelper.createFolder(APP_NAME, null)
            .addOnSuccessListener { googleDriveFileHolder ->
                folderId = googleDriveFileHolder.id
                viewModel.uploadBackup(activity!!.filesDir)
            }
            .addOnFailureListener { e ->
                Log.d("SettingsFragment", "onFailure: " + e.message)
            }
    }

}
