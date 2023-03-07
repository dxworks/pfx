package org.danb.pfx.model.common

open class VariableBaseNode (
    name: String,
    modifier: Modifier,
    var isDollared: Boolean,
    var type: VariableType
) : ASTObject(name, modifier) {
    constructor(): this("", Modifier.None, false, VariableType.Unknown)
}