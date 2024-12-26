import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import java.io.File

abstract class BuildLambdaDeploymentZIPTask : DefaultTask() {
    private val lib = File("lib/")
    private val jarFolderInsideZipFile = "app/lib/"
    private val zipFileGeneratedOnBuildTask = "app/build/distributions/app.zip"
    private val finalZip = File("app.zip")

    @TaskAction
    fun action() {
        this.prepareLibDirectory()
        this.copyJarsToLibDirectory()
        this.zipLibDirectory()
        this.deleteLibDirectory()
    }

    private fun prepareLibDirectory() {
        this.deleteLibDirectory()
        this.lib.mkdir()
    }

    private fun deleteLibDirectory() {
        this.lib.deleteRecursively()
    }

    private fun copyJarsToLibDirectory() {
        ZipFile(this.zipFileGeneratedOnBuildTask).use { zip ->
            zip.entries()
                .asSequence()
                .filter { this.isFile(it) }
                .forEach { entry ->
                    val outputFile = this.createFileInLibFolder(entry)
                    zip.getInputStream(entry).use { input ->
                        outputFile.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }

                }
        }
    }

    private fun isFile(entry: ZipEntry): Boolean {
        return entry.name.startsWith(this.jarFolderInsideZipFile) && !entry.isDirectory
    }

    private fun createFileInLibFolder(entry: ZipEntry): File {
        return File(this.lib, entry.name.removePrefix(this.jarFolderInsideZipFile))
    }

    private fun zipLibDirectory() {
        ZipOutputStream(BufferedOutputStream(FileOutputStream(this.finalZip))).use { zos ->
            this.lib.listFiles()
                ?.forEach { file ->
                    val zipFileName = file.absolutePath.removePrefix(this.lib.absolutePath)
                    zos.putNextEntry(ZipEntry("lib/$zipFileName"))
                    file.inputStream().use { fis -> fis.copyTo(zos) }
                    zos.closeEntry()
                }
        }
    }
}