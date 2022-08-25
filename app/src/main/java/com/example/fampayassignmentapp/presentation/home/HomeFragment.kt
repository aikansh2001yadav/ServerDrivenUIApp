package com.example.fampayassignmentapp.presentation.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.airbnb.epoxy.CarouselModel_
import com.example.fampayassignmentapp.*
import com.example.fampayassignmentapp.databinding.FragmentHomeBinding
import com.example.fampayassignmentapp.domain.models.CardGroup
import com.example.fampayassignmentapp.domain.preferences.CardPreference
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var pref: CardPreference

    private var opened = false
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshHome.setOnRefreshListener {
            viewModel.getCardGroups()
            binding.swipeRefreshHome.isRefreshing = false
            binding.bgErrorImage.isVisible = false
        }

        viewModel.cardGroups.observe(viewLifecycleOwner) { cardGroupList ->
            if (cardGroupList != null) {
                updateUI(cardGroupList)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.progressAnimationView.isVisible = it
                binding.FrameLayout1.isVisible = !it
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.FrameLayout1.isVisible = false
                showAlertSnackBar("Something went wrong. Try Again")
                binding.bgErrorImage.isVisible = true
            }
        }

        viewModel.cardDismissed.observe(viewLifecycleOwner) { booleanStringPair ->
            if (booleanStringPair.first) {
                showSnackBar("Alert Dismissed")
                pref.addCard(booleanStringPair.second)
            } else {
                pref.addCard(booleanStringPair.second)
                pref.addCardTemp(booleanStringPair.second)
                showSnackBar("You will be reminded later")
            }
        }

        viewModel.browserIntent.observe(viewLifecycleOwner) {
            if (it != null) {
                startActivity(it)
            }
        }

    }

    private fun updateUI(cardGroupList: List<CardGroup>) {
        binding.homeRecycler.withModels {
            val displayMetrics = requireContext().resources.displayMetrics
            val dpHeight = (displayMetrics.heightPixels / displayMetrics.density) - 30f
            val dpWidth = (displayMetrics.widthPixels / displayMetrics.density) - 30f
            for (cardGroup in cardGroupList) {
                when (cardGroup.designType) {
                    "HC3" -> {
                        val bindingModel: MutableList<Hc3BindingModel_> = ArrayList()
                        val removedCardList: HashSet<String> = pref.getCardList()
                        for (card in cardGroup.cards!!) {
//                            if (prefs.getBoolean(card!!.title, false)) {
//                                break
//                            }
                            if (!removedCardList.contains(card!!.name)) {
                                if (cardGroup.isScrollable == false) {
                                    val hc3BindingModel_ =
                                        Hc3BindingModel_().id(0).card(card).home(this@HomeFragment)
                                            .viewModel(viewModel).widthPx(dpWidth)
                                            .itemCount(cardGroup.cards.size)
                                    bindingModel.add(hc3BindingModel_)
                                } else {
                                    bindingModel.add(
                                        Hc3BindingModel_().id(0).card(card).home(this@HomeFragment)
                                            .viewModel(viewModel).widthPx(dpWidth).itemCount(1)
                                    )
                                }
                            }
                        }
                        val carouselModel: CarouselModel_ =
                            CarouselModel_().id(cardGroup.id).models(bindingModel)
                        this.add(carouselModel)
                    }
                    "HC5" -> {
                        val bindingModel: MutableList<Hc5BindingModel_> = ArrayList()
                        for (card in cardGroup.cards!!) {
                            if (cardGroup.isScrollable == false) {
                                val hc5BindingModel_ =
                                    Hc5BindingModel_().id(0).card(card).home(this@HomeFragment)
                                        .widthPx(dpWidth)
                                        .itemCount(cardGroup.cards.size)
                                bindingModel.add(hc5BindingModel_)
                            } else {
                                bindingModel.add(
                                    Hc5BindingModel_().id(0).card(card).home(this@HomeFragment)
                                        .widthPx(dpWidth).itemCount(1)
                                )
                            }
                        }
                        val carouselModel: CarouselModel_ =
                            CarouselModel_().id(cardGroup.id).models(bindingModel)
                        this.add(carouselModel)
                    }
                    "HC6" -> {
                        val bindingModel: MutableList<Hc6BindingModel_> = ArrayList()
                        for (card in cardGroup.cards!!) {
                            if (cardGroup.isScrollable == false) {
                                val hc6BindingModel_ =
                                    Hc6BindingModel_().id(0).card(card).home(this@HomeFragment)
                                        .widthPx(dpWidth)
                                        .itemCount(cardGroup.cards.size)
                                bindingModel.add(hc6BindingModel_)
                            } else {
                                bindingModel.add(
                                    Hc6BindingModel_().id(0).card(card).home(this@HomeFragment)
                                        .widthPx(dpWidth).itemCount(1)
                                )
                            }
                        }
                        val carouselModel: CarouselModel_ =
                            CarouselModel_().id(cardGroup.id).models(bindingModel)
                        this.add(carouselModel)
                    }
                    "HC9" -> {
                        val bindingModel: MutableList<Hc9BindingModel_> = ArrayList()
                        for (card in cardGroup.cards!!) {
                            bindingModel.add(
                                Hc9BindingModel_().id(0)
                                    .card(card)
                                    .height((cardGroup.height!!).toInt()).home(this@HomeFragment)
                            )
                        }
                        val carouselModel: CarouselModel_ =
                            CarouselModel_().id(cardGroup.id).models(bindingModel)
                        this.add(carouselModel)
                    }
                    "HC1" -> {
                        val bindingModel: MutableList<Hc1BindingModel_> = ArrayList()
                        for (card in cardGroup.cards!!) {

                            if (cardGroup.isScrollable == false) {
                                val hc1BindingModel: Hc1BindingModel_ =
                                    Hc1BindingModel_().id(0).card(card).home(this@HomeFragment)
                                        .widthPx(dpWidth)
                                        .itemCount(cardGroup.cards.size)
                                bindingModel.add(hc1BindingModel)
                            } else {
                                bindingModel.add(
                                    Hc1BindingModel_().id(0).card(card).home(this@HomeFragment)
                                        .widthPx(dpWidth).itemCount(1)
                                )
                            }
                        }
                        val carouselModel: CarouselModel_ =
                            CarouselModel_().id(cardGroup.id).models(bindingModel)
                        this.add(carouselModel)
                    }
                }
            }
        }
    }

    fun onLongPressResize(v: View?): Boolean {
        val animation: ObjectAnimator = if (!opened) {
            ObjectAnimator.ofFloat(v, "translationX", 450f)
        } else {
            ObjectAnimator.ofFloat(v, "translationX", 0f)
        }
        animation.duration = 250
        animation.start()
        opened = !opened
        return true
    }

    fun onCardClick(url: String?, isDisabled: Boolean?) {
        if (isDisabled == null) {
            if (url != null) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
            }
        } else if (isDisabled == false) {
            if (url != null) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
            }
        }
    }

    private fun showAlertSnackBar(errorText: String) {
        val snackbar = Snackbar
            .make(binding.rootContainer, errorText, Snackbar.LENGTH_LONG)
        snackbar.view.backgroundTintList = ColorStateList.valueOf(Color.RED)
        val textView =
            snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        snackbar.show()
    }

    private fun showSnackBar(errorText: String) {
        val snackbar = Snackbar
            .make(binding.rootContainer, errorText, Snackbar.LENGTH_LONG)
        snackbar.view.backgroundTintList = ColorStateList.valueOf(Color.LTGRAY)
        val textView =
            snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.BLACK)
        snackbar.show()
    }
}