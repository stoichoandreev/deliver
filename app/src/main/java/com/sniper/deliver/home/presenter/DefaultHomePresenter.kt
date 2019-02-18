package com.sniper.deliver.home.presenter

import com.sniper.deliver.home.usecases.GetTopRatedMoviesUseCase
import com.sniper.deliver.home.view_model.HomeItemViewModel
import com.sniper.deliver.mvp.PresenterView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class DefaultHomePresenter(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val disposable: CompositeDisposable
) : HomePresenter {

    private val loadTopRatedMoviesSubject = PublishSubject.create<Unit>()
    private val errorSubject = PublishSubject.create<Throwable>()
    private val loadingSubject = PublishSubject.create<Boolean>()

    private val topRatedMoviesSubject = BehaviorSubject.create<List<HomeItemViewModel>>()
    private var view: HomePresenter.View? = null

    init {
        disposable.add(topRatedMoviesSubject.subscribe { items -> view?.showTopRatesMovies(items) })
        disposable.add(loadingSubject.subscribe { show -> view?.showLoading(show) })
        disposable.add(errorSubject.subscribe { error -> view?.showError(error.localizedMessage) })

        disposable.add(loadTopRatedMoviesSubject
            .doOnNext { loadingSubject.onNext(true) }
            .flatMap { getTopRatedMoviesUseCase.getTopRatedMovies()
                .doOnError { error -> errorSubject.onNext(error) }
                .onErrorResumeNext(Observable.empty())
                .doOnComplete { loadingSubject.onNext(false) } }
            .subscribe{ topRatedMovies -> topRatedMoviesSubject.onNext(topRatedMovies) }
        )
    }

    override fun onFetchTopRatedMovies() {
        when (topRatedMoviesSubject.value) {
            null -> onRefreshTopRatedMovies()
            else -> topRatedMoviesSubject.onNext(topRatedMoviesSubject.value!!)
        }
    }

    override fun onRefreshTopRatedMovies() {
        loadTopRatedMoviesSubject.onNext(Unit)
    }

    override fun attachView(view: PresenterView?) {
        this.view = view as HomePresenter.View?
    }

    override fun destroy() {
        view = null
        disposable.dispose()
    }

}
