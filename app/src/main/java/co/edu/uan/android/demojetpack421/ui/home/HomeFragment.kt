package co.edu.uan.android.demojetpack421.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.edu.uan.android.demojetpack421.apis.Cat
import co.edu.uan.android.demojetpack421.apis.CatApi
import co.edu.uan.android.demojetpack421.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import java.lang.Exception

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewmodel = homeViewModel
        binding.lifecycleOwner = this

        binding.btnChangeText.setOnClickListener {
            homeViewModel.changeText("Nuevo valor")
            loadCats()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun loadCats() {
        binding.viewmodel?.loadCatImageWithRetrofit()
        Picasso
            .get()
            .load(binding.viewmodel?.catUrl?.value)
            .into(binding.catImage)
    }

    fun loadCatImageWithIon() {
        Ion.with(this.activity)
            .load("https://api.thecatapi.com/v1/images/search")
            .asString()
            .setCallback(object: FutureCallback<String> {
                override fun onCompleted(e: Exception?, result: String?) {
                    Log.d("TESTAPI","Result of API: $result" )
                    val cat = Gson().fromJson(result!!, Array<Cat>::class.java)
                    Picasso
                        .get()
                        .load(cat[0].url)
                        .into(binding.catImage)
                }
            })
    }
}