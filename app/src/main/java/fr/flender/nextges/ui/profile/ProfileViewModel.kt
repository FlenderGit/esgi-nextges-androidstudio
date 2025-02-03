package fr.flender.nextges.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ProfileViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        var user = Firebase.auth.currentUser

        if (user == null) {
            value = "Not connected"
        } else {
            var display = user.displayName ?: ""
            if (display.isEmpty()) {
                display = user.email.toString()
            }
            value = "Bienvenue $display"
        }
    }
    val text: LiveData<String> = _text
}