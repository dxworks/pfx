package org.danb.pfx.model.expressions

class ScalarNode (
    var stringValue: String,
    var type: ScalarType
) {
    constructor() : this("", ScalarType.Unknown)
}