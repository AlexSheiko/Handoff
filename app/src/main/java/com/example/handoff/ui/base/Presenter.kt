package com.appigo.todopro.ui.base

import com.example.handoff.ui.base.MvpView

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface Presenter<in V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}