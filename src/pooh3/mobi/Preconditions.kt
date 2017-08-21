package pooh3.mobi

val <T> T?.checkNotNull: T
    get() = this.checkNotNull(null)

fun <T> T?.checkNotNull(msg: String?): T {
    return this ?: throw NullPointerException( msg ?: "reference Object is null")
}

@JvmOverloads
fun Boolean.orThrow(msg: String? = null) {
    if (!this)
        throw IllegalArgumentException(
                msg ?: "argument condition is not required")
}
