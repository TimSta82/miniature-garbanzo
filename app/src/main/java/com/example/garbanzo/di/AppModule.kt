package com.example.garbanzo.di

import com.example.garbanzo.ui.screens.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

//    single { (serverUri: URI, messageListener: (String) -> Unit) -> FinanceWebSocketClient(serverUri, messageListener) }

    viewModelOf(::ProfileViewModel)
}