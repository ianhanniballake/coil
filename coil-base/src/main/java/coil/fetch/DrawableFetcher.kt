package coil.fetch

import android.graphics.drawable.Drawable
import coil.decode.DataSource
import coil.decode.DrawableUtils
import coil.decode.Options
import coil.util.isVector
import coil.util.toDrawable

internal class DrawableFetcher : Fetcher<Drawable> {

    override fun cacheKey(data: Drawable): String? = null

    override suspend fun fetch(data: Drawable, options: Options): FetchResult {
        val isVector = data.isVector
        return DrawableResult(
            drawable = if (isVector) {
                DrawableUtils.convertToBitmap(
                    drawable = data,
                    config = options.config,
                    size = options.size,
                    scale = options.scale,
                    allowInexactSize = options.allowInexactSize
                ).toDrawable(options.context)
            } else {
                data
            },
            isSampled = isVector,
            dataSource = DataSource.MEMORY
        )
    }
}
