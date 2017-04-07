package com.appigo.todopro.ui.base

import com.example.handoff.ui.base.MvpView
import com.example.handoff.util.Extensions

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<T : MvpView> : Presenter<T>, Extensions {

    var mvpView: T? = null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
        checkViewAttached()
    }

    override fun detachView() {
        mvpView = null
    }

    val isViewAttached: Boolean
        get() = mvpView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}
