package org.danb.pfx.model.expressions

enum class ScalarType(val typeInt: kotlin.Int) {
    Int(0),
    Real(1),
    String(2),
    Unknown(3),
    System(4),
    Bin(5);

    companion object {
        private val map = ScalarType.values().associateBy { it.typeInt }
        infix fun from(value: kotlin.Int): ScalarType = map[value]!!
    }
}