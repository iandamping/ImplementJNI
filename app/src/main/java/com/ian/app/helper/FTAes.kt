package com.ian.app.helper

import android.annotation.SuppressLint
import android.util.Base64

import java.security.MessageDigest

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object FTAes {

    fun encrypt(input: String, key: String): String {
        var crypted: ByteArray? = null
        try {
            val e = SecretKeySpec(key.toByteArray(), "AES")
            @SuppressLint("GetInstance") val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(1, e)
            crypted = cipher.doFinal(input.toByteArray())
        } catch (var5: Exception) {
            println(var5.toString())
        }

        return if (crypted != null) {
            String(Base64.encode(crypted, Base64.NO_WRAP))
        } else {
            ""
        }
    }

    fun decrypt(input: String, key: String): String {
        var output: ByteArray? = null
        try {
            val e = SecretKeySpec(key.toByteArray(), "AES")
            @SuppressLint("GetInstance") val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(2, e)
            output = cipher.doFinal(Base64.decode(input, Base64.NO_WRAP))
        } catch (var5: Exception) {
            println(var5.toString())
        }

        return output?.let { String(it) } ?: ""
    }

    fun sha256(base: String): String {
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(base.toByteArray(charset("UTF-8")))
            val hexString = StringBuffer()

            hash.indices.forEachIndexed { index, i ->
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            return hexString.toString()
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }

    }

}