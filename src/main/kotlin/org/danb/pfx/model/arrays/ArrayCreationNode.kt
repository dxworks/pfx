package org.danb.pfx.model.arrays

class ArrayCreationNode(
    var hasArrayKey: Boolean,
    var arrayElements: MutableList<ArrayElementNode>
) {
    constructor() : this(false, mutableListOf())
}