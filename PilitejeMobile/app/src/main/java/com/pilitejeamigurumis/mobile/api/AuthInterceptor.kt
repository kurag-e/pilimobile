package com.pilitejeamigurumis.mobile.api

import com.pilitejeamigurumis.mobile.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {

    // Ajusta según tus rutas públicas de backend
    private val publicPaths = listOf("/api/auth/login", "/api/auth/register", "/api/productos")

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val urlPath = original.url.encodedPath

        // Si es pública → no agregamos token
        if (publicPaths.any { urlPath.startsWith(it) }) {
            return chain.proceed(original)
        }

        val token = sessionManager.getToken()
        val request = if (token != null) {
            original.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            original
        }
        return chain.proceed(request)
    }
}
