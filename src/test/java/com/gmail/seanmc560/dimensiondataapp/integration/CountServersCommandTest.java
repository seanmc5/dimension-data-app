package com.gmail.seanmc560.dimensiondataapp.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=" + false})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CountServersCommandTest {

  @Autowired
  private Shell shell;

  @Test
  public void countCommandTest() {
    assertThat(shell.evaluate(() -> "count-servers")).isEqualTo("--- OK ---\nCount: 0");
    createServer("server1", "description1");
    assertThat(shell.evaluate(() -> "count-servers")).isEqualTo("--- OK ---\nCount: 1");
    createServer("server2", "description2");
    assertThat(shell.evaluate(() -> "count-servers")).isEqualTo("--- OK ---\nCount: 2");
    deleteServer(2);
    assertThat(shell.evaluate(() -> "count-servers")).isEqualTo("--- OK ---\nCount: 1");
  }

  private void deleteServer(final int id) {
    shell.evaluate(() -> String.format("delete-server %d", id));
  }

  private void createServer(final String name, final String description) {
    shell.evaluate(() -> String.format("create-server %s %s", name, description));
  }
}