package org.danb.pfx.io.scanner

import org.danb.pfx.ast.ASTCreator
import org.danb.pfx.utils.filefinder.FileFinder
import org.danb.pfx.utils.logger
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

object ProjectScanner {

    private val logger = logger()

    private val astCreator = ASTCreator()

    fun scanForProject(path: Path) {
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                logger.info("Directory detected: {}", path.toString())
                scanDirectory(path)
            }
        }
    }

    private fun scanDirectory(path: Path) {
        logger.info("Scanning path for PHP files")
        val files = FileFinder.findSourceFiles(path)
        files.phpFiles.forEach {
            println(it)
        }
        astCreator.createAst(files.phpFiles)
    }

}