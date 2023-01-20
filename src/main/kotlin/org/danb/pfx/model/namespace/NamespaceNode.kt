package org.danb.pfx.model.namespace

class NamespaceNode(var nameSegments: MutableList<String>) {
    constructor() : this(mutableListOf())
}