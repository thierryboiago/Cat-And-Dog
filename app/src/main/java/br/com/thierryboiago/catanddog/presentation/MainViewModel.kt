package br.com.thierryboiago.catanddog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.thierryboiago.catanddog.data.model.Animal
import br.com.thierryboiago.catanddog.domain.LoadCatUseCase
import br.com.thierryboiago.catanddog.domain.LoadDogUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel (private val loadCatUseCase: LoadCatUseCase, private val loadDogUseCase: LoadDogUseCase
) : ViewModel() {

    private val _repos = MutableLiveData<State>()
    val repos: LiveData<State> = _repos

    fun getAnimal(user: String) {
        if(user.equals("cat")){
            viewModelScope.launch {
                loadCatUseCase(user)
                    .onStart {
                        _repos.postValue(State.Loading)
                    }
                    .catch {
                        _repos.postValue(State.Error(it))
                    }
                    .collect {
                        _repos.postValue(State.Success(it))
                    }
            }
        }else{
            viewModelScope.launch {
                loadDogUseCase(user)
                    .onStart {
                        _repos.postValue(State.Loading)
                    }
                    .catch {
                        _repos.postValue(State.Error(it))
                    }
                    .collect {
                        _repos.postValue(State.Success(it))
                    }
            }
        }

    }

    sealed class State {
        object Loading : State()
        data class Success(val animals: List<Animal>) : State()
        data class Error(val error: Throwable) : State()
    }

}