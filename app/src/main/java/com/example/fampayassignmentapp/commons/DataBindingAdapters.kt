package com.example.fampayassignmentapp.commons

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.View.*
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyRecyclerView
import com.bumptech.glide.Glide
import com.example.fampayassignmentapp.Hc3CtaBindingModel_
import com.example.fampayassignmentapp.commons.Utils.toPx
import com.example.fampayassignmentapp.domain.models.BgGradient
import com.example.fampayassignmentapp.domain.models.CTAItem
import com.example.fampayassignmentapp.domain.models.Entity
import com.example.fampayassignmentapp.presentation.home.HomeFragment

object DataBindingAdapters {
    @BindingAdapter("imageURL")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context).load(url).into(view)
    }

    @BindingAdapter("imageURL", "aspect_ratio")
    @JvmStatic
    fun loadAspectImage(view: ImageView, url: String?, aspectRatio: Double?) {
        if (aspectRatio != null) {
            Glide.with(view.context).load(url).override(
                (aspectRatio * view.width).toInt(),
                ((1.00 - aspectRatio) * view.height).toInt()
            ).into(view)
        } else {
            loadImage(view, url)
        }
    }

    @BindingAdapter("backgroundCardColor")
    @JvmStatic
    fun changeBackgroundColor(view: CardView, bgColor: String?) {
        var bg_color = bgColor
        if (bg_color == null) {
            bg_color = "#FBAF03"
        }
        view.backgroundTintList = ColorStateList.valueOf(Color.parseColor(bg_color))
    }

    @BindingAdapter("backgroundGradientColor")
    @JvmStatic
    fun changeBackgroundGradientColor(view: View, bgGradient: BgGradient?) {

        if (bgGradient != null) {
            val angle = bgGradient.angle ?: 0
            val orientation = if (angle >= 0) {
                when (angle) {
                    0 -> GradientDrawable.Orientation.LEFT_RIGHT
                    45 -> GradientDrawable.Orientation.BL_TR
                    90 -> GradientDrawable.Orientation.BOTTOM_TOP
                    135 -> GradientDrawable.Orientation.BR_TL
                    180 -> GradientDrawable.Orientation.RIGHT_LEFT
                    225 -> GradientDrawable.Orientation.TR_BL
                    270 -> GradientDrawable.Orientation.TOP_BOTTOM
                    315 -> GradientDrawable.Orientation.TL_BR
                    else -> {
                        GradientDrawable.Orientation.TOP_BOTTOM
                    }
                }
            } else {
                GradientDrawable.Orientation.TOP_BOTTOM
            }
            val colors = arrayListOf<Int>()
            for (colorString in bgGradient.colors!!) {
                colors.add(Color.parseColor(colorString))
            }
            val gd = GradientDrawable(orientation, colors.toIntArray())
            gd.cornerRadius = 12f
            gd.shape = GradientDrawable.RECTANGLE
            view.background = gd
            view.layoutParams.width = 250
            view.layoutParams.height = 200
        }
    }


    @BindingAdapter("onClick")
    @JvmStatic
    fun clickRedirect(view: CardView?, url: String?) {
    }

    @BindingAdapter("layout_height")
    @JvmStatic
    fun setLayoutHeight(view: View, height: Float) {
        val layoutParams = view.layoutParams
        layoutParams.height = height.toPx.toInt()
        view.layoutParams = layoutParams
    }

    @BindingAdapter("layout_width")
    @JvmStatic
    fun setLayoutWidth(view: View, width: Float) {
        val layoutParams = view.layoutParams
        layoutParams.width = width.toPx.toInt()
        view.layoutParams = layoutParams
    }

    @BindingAdapter("original_text", "entities", "home", "isDisabled")
    @JvmStatic
    fun setFormattedText(
        view: TextView,
        originalText: String,
        entities: List<Entity>,
        home: HomeFragment,
        isDisabled: Boolean?
    ) {
        if (entities.isEmpty()) {
            view.text = originalText
        } else {
            view.movementMethod = LinkMovementMethod()
            view.text = format(originalText, entities.toList(), home, isDisabled)
        }
    }

    @JvmStatic
    fun format(
        text: String,
        entities: List<Entity>,
        home: HomeFragment,
        isDisabled: Boolean?
    ): SpannableString {
        var str = SpannableString(text)

        val strLowerCase = text.lowercase()

        for (entity in entities) {
            val textPosition = strLowerCase.indexOf(entity.text!!.lowercase())
            val color =
                Color.parseColor(entity.color!!)
            val foregroundColorSpan = ForegroundColorSpan(color)
            str.setSpan(
                foregroundColorSpan,
                textPosition,
                textPosition + entity.text.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            if (entity.url != null) {
                str.setSpan(
                    object : URLSpan(entity.url) {
                        override fun updateDrawState(ds: TextPaint) {
                            ds.isUnderlineText = false
                        }
                    },
                    textPosition,
                    textPosition + entity.text.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            if (entity.fontStyle == "bold") {
                str.setSpan(
                    StyleSpan(Typeface.BOLD),
                    textPosition,
                    textPosition + entity.text.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else if (entity.fontStyle == "italic") {
                str.setSpan(
                    StyleSpan(Typeface.ITALIC),
                    textPosition,
                    textPosition + entity.text.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else if (entity.fontStyle == "underline") {
                str.setSpan(
                    UnderlineSpan(), textPosition,
                    textPosition + entity.text.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return str
    }

    @BindingAdapter("listOfCTA", "home")
    @JvmStatic
    fun EpoxyRecyclerView.setupCTARecyclerView(ctaList: List<CTAItem>, home: HomeFragment) {
        withModels {
            val bindingList: MutableList<Hc3CtaBindingModel_> = ArrayList()
            for (ctaItem in ctaList) {
                bindingList.add(Hc3CtaBindingModel_().id(0).home(home).cta(ctaItem))
            }
            val carouselModel: CarouselModel_ =
                CarouselModel_().id(0).models(bindingList)
            this.add(carouselModel)
        }
    }

    @BindingAdapter("alignment")
    @JvmStatic
    fun align(view: View, alignment: String?) {
        if (alignment != null) {
            if (alignment.lowercase() == "left") {
                view.textAlignment = TEXT_ALIGNMENT_TEXT_START
            } else if (alignment.lowercase() == "center") {
                view.textAlignment = TEXT_ALIGNMENT_CENTER
            } else if (alignment.lowercase() == "right") {
                view.textAlignment = TEXT_ALIGNMENT_TEXT_END
            }
        }
    }

    @BindingAdapter("cta_text_color")
    @JvmStatic
    fun changeTextColor(view: TextView, textColor: String?) {
        if (textColor != null) {
            view.setTextColor(Color.parseColor(textColor))
        }
    }
}