package org.danb.pfx.model.expressions

import org.danb.pfx.model.common.Modifier

class VariableNode(
    var isDollared: Boolean,
    var name: String,
    var modifier: Modifier?
) {
    constructor() : this(false, "", Modifier.None)
}