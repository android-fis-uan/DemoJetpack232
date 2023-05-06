package co.edu.uan.android.demojetpack421.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uan.android.demojetpack421.apis.CatApi
import co.edu.uan.android.demojetpack421.databases.CatDatabase
import co.edu.uan.android.demojetpack421.databases.CatEntity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(app: Application) : AndroidViewModel(app) {

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
            val cat = cats.get(0)
            catUrl.value = cat.url
            val db = CatDatabase.getInstance(getApplication())
            db.catDao().save(CatEntity(cat.id, cat.url, cat.width, cat.height))
        }

    }


}