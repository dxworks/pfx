package org.danb.pfx.model.methods

class MethodParameter(
    var parameterType: String,
    var parameterName: String,
    var isDollared: Boolean,
    var isMandatory: Boolean,
    var isVariadic: Boolean
) {
    constructor() : this("", "", false, false, false)
}