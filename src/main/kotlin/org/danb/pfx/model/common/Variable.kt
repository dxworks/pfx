package org.danb.pfx.model.common

class Variable(
    var isDollared: Boolean,
    var name: String,
    var modifier: Modifier?
) {
    constructor() : this(false, "", null)
}