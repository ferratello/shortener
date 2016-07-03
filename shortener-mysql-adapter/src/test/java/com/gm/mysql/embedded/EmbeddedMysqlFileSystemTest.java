package com.gm.mysql.embedded;

import com.mysql.management.MysqldResourceI;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class EmbeddedMysqlFileSystemTest
{
  @Rule
  public final JUnitRuleMockery mockery = new JUnitRuleMockery();
  private final EmbeddedMysqlFileSystem underTest = new EmbeddedMysqlFileSystem();

  @Test
  public void cleanFileSystem()
  {
    MysqldResourceI mysqldResource = mockery.mock(MysqldResourceI.class);
    mockery.checking(new Expectations()
    {{
      oneOf(mysqldResource).getBaseDir();
      will(returnValue(new File("/tmp/test")));
    }});

    underTest.cleanFileSystem(mysqldResource);
  }

  @Test
  public void getSqlStringFromResource()
  {
    assertThat(underTest.getInitSql("init-db.sql"), containsString("CREATE TABLE"));
  }

  @Test(expected = RuntimeException.class)
  public void notExistentResource()
  {
    underTest.getInitSql("not_existing.sql");
  }
}