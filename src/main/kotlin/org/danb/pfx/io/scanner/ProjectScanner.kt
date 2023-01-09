package org.danb.pfx.io.scanner

import org.danb.pfx.utils.logger
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

object ProjectScanner {

    private val logger = logger()

    fun scanForProject(path: Path) {
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                logger.info("Directory detected: {}", path.toString())
                File(path.toString()).walkTopDown().forEach {
                    println(it)
                }
            }
        }
    }

}