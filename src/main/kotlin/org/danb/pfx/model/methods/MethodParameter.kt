package org.danb.pfx.model.methods

class MethodParameter(
    var parameterType: String,
    var parameterName: String,
    var isDollared: Boolean
) {
    constructor() : this("", "", false)
}