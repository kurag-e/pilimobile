package com.pilitejeamigurumis.mobile.utils

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("piliteje_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "accessToken"
        private const val KEY_ROLE = "role"
        private const val KEY_USER_ID = "userId"
        private const val KEY_USER_NAME = "userName"
        private const val KEY_USER_EMAIL = "userEmail"
    }

    fun saveSession(
        token: String,
        role: String,
        userId: Long,
        name: String,
        email: String
    ) {
        prefs.edit()
            .putString(KEY_TOKEN, token)
            .putString(KEY_ROLE, role)
            .putLong(KEY_USER_ID, userId)
            .putString(KEY_USER_NAME, name)
            .putString(KEY_USER_EMAIL, email)
            .apply()
    }

    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)
    fun getRole(): String? = prefs.getString(KEY_ROLE, null)
    fun getUserId(): Long = prefs.getLong(KEY_USER_ID, -1L)
    fun getUserName(): String? = prefs.getString(KEY_USER_NAME, null)
    fun getUserEmail(): String? = prefs.getString(KEY_USER_EMAIL, null)

    fun isLoggedIn(): Boolean = getToken() != null

    fun clear() {
        prefs.edit().clear().apply()
    }
}
