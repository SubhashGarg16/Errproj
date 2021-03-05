package eu.welfare.newneeds

import android.app.Application
import eu.welfare.newneeds.data.db.AppDatabase
import eu.welfare.newneeds.data.network.MyApi
import eu.welfare.newneeds.data.preferences.PreferenceProvider
import eu.welfare.newneeds.data.repositories.ProductsRepository
import eu.welfare.newneeds.data.repositories.QuotesRepository
import eu.welfare.newneeds.data.repositories.UserRepository
import eu.welfare.newneeds.ui.auth.AuthViewModelFactory
import eu.welfare.newneeds.ui.home.products.ProductsViewModelFactory
import eu.welfare.newneeds.ui.home.profile.ProfileViewModelFactory
import eu.welfare.newneeds.ui.home.quotes.QuotesViewModelFactory
import net.simplifiedcoding.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

// This is application class i.e. base class for our application
class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }

        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from singleton { QuotesRepository(instance(),instance(),instance()) }
        bind() from singleton { ProductsRepository(instance())}

        // We don't want the single instance of AuthViewModelfActory so we use the kyword provider
        bind() from provider { AuthViewModelFactory(instance()) }

        bind() from provider { ProfileViewModelFactory(instance()) }

        bind() from provider { QuotesViewModelFactory(instance()) }

        bind() from provider { ProductsViewModelFactory(instance())}


    }



}