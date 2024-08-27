package tech.capitalcoding.pokedex.basic_feature.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class SearchTypeListResult<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val items: @RawValue List<T>,
    val nextOffset: Int? = null,
    val previousOffset: Int? = null,
): Parcelable {

    companion object {
        fun <T> empty(): SearchTypeListResult<T> = SearchTypeListResult(
            count = 0,
            next = null,
            previous = null,
            items = emptyList()
        )
    }

}
