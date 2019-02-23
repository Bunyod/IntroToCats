import java.io.{File, FileInputStream, FileOutputStream}
import java.util.zip.{GZIPOutputStream, ZipEntry, ZipInputStream, ZipOutputStream}

object Streaming extends App {

  def makeZipFiles(sourceFiles: List[File], zipFileName: String): Unit = {
    val buffer: Array[Byte] = new Array[Byte](1024)
    sourceFiles.foreach(sourceFile => {
      val fis: FileInputStream = new FileInputStream(sourceFile)
      using(new ZipOutputStream(new FileOutputStream(zipFileName))) { zos =>
        zos.putNextEntry(new ZipEntry(sourceFile.getName))
        Stream.continually(fis.read(buffer)).takeWhile(_ != -1).foreach(zos.write(buffer, 0, _))
      }
    })
  }

  def unzipFile(fileName: String) = {
    using(new ZipInputStream( new FileInputStream(fileName))) { zis =>
      Stream.continually(zis.getNextEntry).takeWhile(_ != null).foreach { file =>
        println(file.getName)
        val buffer = new Array[Byte](1024)
        using(new FileOutputStream(file.getName)) { fout =>
          Stream.continually(zis.read(buffer)).takeWhile(_ > 0).foreach(fout.write(buffer, 0, _))
        }
      }
    }
  }

  def makeZipFile(sourceFile: String, zipFileName: String): Unit = {
    val buffer = new Array[Byte](1024)
    val fileInput = new FileInputStream(sourceFile)
    using(new GZIPOutputStream(new FileOutputStream(zipFileName))) { gzipOuputStream =>
      Stream.continually(fileInput.read(buffer)).takeWhile(_ > 0).foreach(gzipOuputStream.write(buffer, 0, _))
    }
  }

  def using[T <: { def close() }, U](resource: T)(block: T => U): U = {
    try {
      block(resource)
    } finally {
      if (resource != null) {
        resource.close()
      }
    }
  }

  println(makeZipFile("/tmp/adsf.csv", "/tmp/newzip.csv.gz"))
//  println(unzipFile("/tmp/newzip.csv.gz"))

}
