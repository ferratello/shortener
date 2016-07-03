package com.gm.mysql.embedded;

import com.mysql.management.MysqldResourceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


class EmbeddedMysqlFileSystem
{
  private static final Logger logger = LoggerFactory.getLogger(EmbeddedMysql.class);

  String getInitSql(String sqlPath)
  {
    String initSql;
    try
    {
      initSql = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(sqlPath).toURI())));
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }

    return initSql;
  }

  void cleanFileSystem(MysqldResourceI mysqldResource)
  {
    try
    {
      Files.walkFileTree(mysqldResource.getBaseDir().toPath(), new FileVisitor<Path>()
      {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
        {
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
          Files.delete(file);
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
        {
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
        {
          Files.delete(dir);
          return FileVisitResult.CONTINUE;
        }
      });
    }
    catch(IOException e)
    {
      logger.warn("Unable to clean FileSystem after shutdown: clean it manually (" + mysqldResource.getBaseDir() + ")\n " +
                      "Stack Trace:\n " + e.getMessage());
    }
  }

}
