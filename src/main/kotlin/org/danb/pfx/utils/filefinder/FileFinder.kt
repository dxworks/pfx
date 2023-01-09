package org.danb.pfx.utils.filefinder

import java.nio.file.Files
import java.nio.file.Path

object FileFinder {

    fun findSourceFiles(dirPath: Path): PHPSourceFiles {
        val fileVisitor = FileVisitor()
        Files.walkFileTree(dirPath, fileVisitor)
        return PHPSourceFiles(fileVisitor.phpFiles)
    }

}