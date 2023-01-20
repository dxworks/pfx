package org.danb.pfx.ast

import org.eclipse.php.core.PHPVersion
import org.eclipse.php.core.ast.nodes.*
import java.io.FileReader

class ASTCreator {

    private val visitor = ASTVisitor()

    fun createAst(phpFiles: List<String>) {
        phpFiles.forEach {
            val parser = ASTParser.newParser(PHPVersion.getLatestVersion(), true, true)
            parser.setSource(FileReader(it))
            val program = parser.createAST(null)
            this.visitor.visit(program)
        }
    }

}