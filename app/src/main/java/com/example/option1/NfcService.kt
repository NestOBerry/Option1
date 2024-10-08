package com.example.option1

import android.nfc.cardemulation.HostApduService
import android.os.Bundle

class NfcService : HostApduService() {
    override fun processCommandApdu(commandApdu: ByteArray, extras: Bundle): ByteArray {
        // Process the incoming APDU command and return a response
        val url = "https://www.google.com"
        return createApduResponse(url)
    }

    override fun onDeactivated(reason: Int) {
        // Handle NFC deactivation events if necessary
    }

    private fun createApduResponse(url: String): ByteArray {
        val urlBytes = url.toByteArray()
        val response = ByteArray(urlBytes.size + 2)
        System.arraycopy(urlBytes, 0, response, 0, urlBytes.size)
        response[response.size - 2] = 0x90.toByte() // SW1: Status word 1 - Success
        response[response.size - 1] = 0x00.toByte() // SW2: Status word 2 - No Additional Information
        return response
    }
}