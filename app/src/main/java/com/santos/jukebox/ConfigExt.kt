package com.santos.jukebox

fun BuildConfig.isClient() = BuildConfig.FLAVOR == "client"