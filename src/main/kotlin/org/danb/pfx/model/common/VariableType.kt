package org.danb.pfx.model.common

enum class VariableType(val typeInt: kotlin.Int) {
    Int(0),
    Real(1),
    String(2),
    Unknown(3),
    System(4),
    Bin(5),
    Array(6);

    companion object {
        private val map = VariableType.values().associateBy { it.typeInt }
        infix fun from(value: kotlin.Int): VariableType = map[value]!!
    }
}