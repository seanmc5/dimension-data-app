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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.annotation.DirtiesContext.ClassMode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=" + false})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CreateServerCommandTest {

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
  public void createCommandTest() {
    assertThat(shell.evaluate(() -> "create-server Server1 Description1"))
      .isEqualTo(
        "--- Created ---\n" +
          "id: 1, name: Server1, description: Description1, createdDate: 1916-04-24T08:00:00Z, lastUpdatedDate: 1916-04-24T08:00:00Z"
      );
  }
}
