package co.edu.uan.android.demojetpack421.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uan.android.demojetpack421.apis.CatApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    //var text:String = ""

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val score = MutableLiveData<Int>().apply {
        value = 10
    }

    val catUrl = MutableLiveData<String>()

    fun changeText(t: String) {
        this._text.value = "This is the new text: $t"
        this.score.value = this.score.value?.times(2)
    }

    fun loadCatImageWithRetrofit() {
        viewModelScope.launch {
            val api = CatApi.getInstance()
            val cats = api.getCats()
            catUrl.value = cats.get(0).url
        }

    }


}