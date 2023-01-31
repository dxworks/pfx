package org.danb.pfx.model.common

import org.danb.pfx.model.namespace.NamespaceNode
import org.danb.pfx.model.statements.imports.UseStatementNode

class FileModel(
    var name: String,
    var namespace: NamespaceNode?,
    var useStatements: MutableList<UseStatementNode>,
    var includedClass: Class
) {
    constructor(): this("", null, mutableListOf(), Class())
}