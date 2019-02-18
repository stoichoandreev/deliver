package com.sniper.deliver.home.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.sniper.deliver.R
import com.sniper.deliver.di.Scope
import com.sniper.deliver.home.adapter.HomeAdapter
import com.sniper.deliver.home.cache.HomeCache
import com.sniper.deliver.home.di.homeModule
import com.sniper.deliver.home.presenter.HomePresenter
import com.sniper.deliver.home.view_model.HomeItemViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.standalone.StandAloneContext


class HomeActivity : AppCompatActivity(), HomePresenter.View {

    private lateinit var presenter: HomePresenter
    private val screenScope = Scope.HOME

    private val homeAdapter: HomeAdapter by lazy(LazyThreadSafetyMode.NONE) { HomeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        injectScreenModule()
        setupToolBar()
        setupScreenAdapter()
        setupRefresh()

        presenter.attachView(this)
        presenter.onFetchTopRatedMovies()
    }

    private fun setupRefresh() {
        swipeToRefreshView.setOnRefreshListener { presenter.onRefreshTopRatedMovies() }
    }

    private fun injectScreenModule() {
        val viewModel = ViewModelProviders.of(this).get(HomeCache::class.java)
        if (viewModel.hasCache()) {
            presenter = viewModel.presenter!!
        } else {
            StandAloneContext.loadKoinModules(homeModule)
            bindScope(getOrCreateScope(screenScope))
            presenter = inject<HomePresenter>().value
            viewModel.presenter = presenter
        }
    }

    private fun setupToolBar() {
        supportActionBar?.title = resources.getString(R.string.home_screen_name)
    }

    private fun setupScreenAdapter() {
        with(homeRecyclerView) { adapter = homeAdapter }
    }

    override fun showTopRatesMovies(movies: List<HomeItemViewModel>) {
        homeAdapter.setData(movies)
    }

    override fun showError(errorMessage: String) {
        val view: View? = findViewById(android.R.id.content)
        when (view) {
            null -> Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            else -> Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showLoading(show: Boolean) {
        swipeToRefreshView.isRefreshing = show
    }

}
