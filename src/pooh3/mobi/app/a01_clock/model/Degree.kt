package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.checkNotNull


class Degree(val int: Int) {

    operator fun plus(degree: Degree): Degree {
        return Degree(this.int + degree.checkNotNull.int)
    }

    operator fun minus(degree: Degree): Degree {
        return Degree(this.int - degree.checkNotNull.int)
    }

    override fun toString(): String {
        return "[$int degree]"
    }

}

fun Degree.minusAbs(degree: Degree): Degree {
    return (this - degree.checkNotNull).absDegree
}

fun Degree.minusMin(degree: Degree): Degree {
    return this.minusAbs(degree.checkNotNull).let {
        return@let if (it.int <= 180) it else 360.degree.minus(it)
    }
}

val Int.degree: Degree
    get() = Degree(this)

val Degree.absDegree: Degree
    get() = Math.abs(this.int).degree