package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.Preconditions.checkNotNull

class Degree private constructor(val value: Int) {

    operator fun minus(degree: Degree): Degree {
        return Degree.of(this.value - checkNotNull(degree).value)
    }

    fun minusAbs(degree: Degree): Degree {
        return Degree.of(Math.abs(
                minus(checkNotNull(degree)).value))
    }

    fun minusMin(degree: Degree): Degree {
        return checkNotNull(minusAbs(degree)).let {
            if (it.value <= 180) it else Degree.of(360).minusMin(it)
        }
    }

    companion object {

        fun of(degreeInt: Int): Degree {
            return Degree(degreeInt)
        }
    }
}
