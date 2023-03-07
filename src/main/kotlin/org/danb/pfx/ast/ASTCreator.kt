package org.danb.pfx.ast

import org.danb.pfx.model.common.FileModel
import org.danb.pfx.model.common.OutputModel
import org.eclipse.php.core.PHPVersion
import org.eclipse.php.core.ast.nodes.*
import java.io.FileReader

class ASTCreator {

    //todo extend AST and ASTParser and have the ASTParser set a binding resolver in your AST
    private val visitor = ASTVisitor()

    fun createAst(phpFiles: List<String>) {
        val files: MutableList<FileModel> = mutableListOf()
        phpFiles.forEach {
            val parser = ASTParser.newParser(PHPVersion.getLatestVersion(), true, true)
            parser.setSource(FileReader(it))
            val program = parser.createAST(null)
            visitor.currentFile = FileModel()
            visitor.currentFile.name = it
            this.visitor.visit(program)
            files.add(visitor.currentFile)
        }
        val outputModel = OutputModel(files)
        //todo modify return type of method to return the outputModel
    }

}