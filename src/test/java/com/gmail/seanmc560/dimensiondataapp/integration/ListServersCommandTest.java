package com.gmail.seanmc560.dimensiondataapp.integration;

import com.gmail.seanmc560.dimensiondataapp.services.DateTimeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.annotation.DirtiesContext.ClassMode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=" + false})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ListServersCommandTest {
  @Autowired
  private Shell shell;

  @MockBean
  private DateTimeService dateTimeService;

  private final static ZonedDateTime TEST_DATE_TIME = ZonedDateTime.of(1916, 4, 24, 8, 0, 0, 0, ZoneOffset.UTC);

  @Before
  public void setup() {
    when(dateTimeService.getCurrentDateTime()).thenReturn(TEST_DATE_TIME);
  }

  @Test
  public void listServerCommandTest() {
    // create a bunch of servers
    shell.evaluate(() -> "create-server Server1 Description1");
    shell.evaluate(() -> "create-server SerFindThisver2 Description2");
    shell.evaluate(() -> "create-server SuperServer Super");
    shell.evaluate(() -> "create-server oldServer TESTfindmeTest");

    assertThat(shell.evaluate(() -> "list-servers")).isEqualTo(
      "--- OK ---\n" +
        " --- Servers --- \n" +
        "id: 1, name: Server1, description: Description1, createdDate: 1916-04-24T08:00:00Z, lastUpdatedDate: 1916-04-24T08:00:00Z\n" +
        "id: 2, name: SerFindThisver2, description: Description2, createdDate: 1916-04-24T08:00:00Z, lastUpdatedDate: 1916-04-24T08:00:00Z\n" +
        "id: 3, name: SuperServer, description: Super, createdDate: 1916-04-24T08:00:00Z, lastUpdatedDate: 1916-04-24T08:00:00Z\n" +
        "id: 4, name: oldServer, description: TESTfindmeTest, createdDate: 1916-04-24T08:00:00Z, lastUpdatedDate: 1916-04-24T08:00:00Z\n" +
        " --- Page Info --- \n" +
        "size: 20, totalElements: 4, totalPages: 1, number: 0"
    );

    assertThat(shell.evaluate(() -> "list-servers --page 0 --size 1")).isEqualTo(
      "--- OK ---\n" +
        " --- Servers --- \n" +
        "id: 1, name: Server1, description: Description1, createdDate: 1916-04-24T08:00:00Z, lastUpdatedDate: 1916-04-24T08:00:00Z\n" +
        " --- Page Info --- \n" +
        "size: 1, totalElements: 1, totalPages: 4, number: 0"
    );

    assertThat(shell.evaluate(() -> "list-servers --name-containing FindThis --page 0 --size 100")).isEqualTo(
      "--- OK ---\n" +
        " --- Servers --- \n" +
        "id: 2, name: SerFindThisver2, description: Description2, createdDate: 1916-04-24T08:00:00Z, lastUpdatedDate: 1916-04-24T08:00:00Z\n" +
        " --- Page Info --- \n" +
        "size: 100, totalElements: 1, totalPages: 1, number: 0"
    );

    assertThat(shell.evaluate(() -> "list-servers --description-containing findme --page 0 --size 29")).isEqualTo(
      "--- OK ---\n" +
        " --- Servers --- \n" +
        "id: 4, name: oldServer, description: TESTfindmeTest, createdDate: 1916-04-24T08:00:00Z, lastUpdatedDate: 1916-04-24T08:00:00Z\n" +
        " --- Page Info --- \n" +
        "size: 29, totalElements: 1, totalPages: 1, number: 0"
    );
  }
}
