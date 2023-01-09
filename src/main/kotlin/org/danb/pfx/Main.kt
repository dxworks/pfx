package org.danb.pfx

import org.danb.pfx.io.scanner.ProjectScanner
import java.nio.file.Path
import java.nio.file.Paths

fun getPathFromName(pathName: String): Path = Paths.get(pathName).toAbsolutePath().normalize()

fun main(args: Array<String>) {
    val path = if (args.isNotEmpty()) {
        getPathFromName(args[0])
    } else {
        getPathFromName(".")
    }

    ProjectScanner.scanForProject(path)
}