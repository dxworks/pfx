package org.danb.pfx.model.expressions

import org.danb.pfx.model.common.AbstractReturnType

class ScalarNode (
    var stringValue: String,
    var type: ScalarType
) : AbstractReturnType {
    constructor() : this("", ScalarType.Unknown)
}