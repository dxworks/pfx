package org.danb.pfx.model.common

import org.danb.pfx.model.methods.Method

class Class(
    name: String,
    modifier: Modifier,
    var methods: MutableList<Method>,
    var fields: MutableList<Field>
    //todo ask about superclasses from "default" PHP packages, aka not files already in project
    //todo see where you can place the binding resolver to get the bindings
) : ASTObject(name, modifier) {
    constructor() : this("", Modifier.None, mutableListOf(), mutableListOf())
}