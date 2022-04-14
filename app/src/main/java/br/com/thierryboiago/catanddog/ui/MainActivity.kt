package br.com.thierryboiago.catanddog.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.thierryboiago.catanddog.R
import br.com.thierryboiago.catanddog.core.createDialog
import br.com.thierryboiago.catanddog.core.createProgressDialog
import br.com.thierryboiago.catanddog.databinding.ActivityMainBinding
import br.com.thierryboiago.catanddog.presentation.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewModel.repos.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    println("sucesso")
                    for ( s in it.animals){
                        Glide.with(binding.root.context)
                            .applyDefaultRequestOptions(
                                RequestOptions()
                                .placeholder(R.drawable.loading)
                                .error(R.drawable.error))
                            .load(s.url).into(binding.imgcatdog)
                    }



                }
            }
        }



        binding.swipeBtn.setOnSwipeAnimationListener { isRight ->
            if (isRight) {
                viewModel.getAnimal("cat")
            } else {
                viewModel.getAnimal("dog")
            }
        }
    }

}