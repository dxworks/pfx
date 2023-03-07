package org.danb.pfx.model.common

class Field(
    //todo add an expression value
    name: String,
    modifier: Modifier,
    isDollared: Boolean,
    type: VariableType,
    var value: String?
) : VariableBaseNode(name, modifier, isDollared, type) {
    constructor() : this("", Modifier.None, false, VariableType.Unknown, null)
}