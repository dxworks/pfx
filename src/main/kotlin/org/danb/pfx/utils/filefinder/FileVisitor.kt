package org.danb.pfx.utils.filefinder

import java.io.IOException
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

class FileVisitor: SimpleFileVisitor<Path?>() {

    private val phpMatcher: PathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.php")

    val phpFiles: MutableList<String>

    init {
        phpFiles = ArrayList()
    }

    override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
        file?.let { visitedFile ->
            val name = visitedFile.fileName
            attrs?.let {
                if (it.isRegularFile) {
                    if (phpMatcher.matches(name)) {
                        phpFiles.add(visitedFile.toString())
                    }
                }
            }
        }
        return FileVisitResult.CONTINUE
    }

    override fun visitFileFailed(file: Path?, exc: IOException?): FileVisitResult {
        exc?.printStackTrace()
        return super.visitFileFailed(file, exc)
    }

}