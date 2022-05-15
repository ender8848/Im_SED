package ic.doc;

import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class WeatherDotComTemperatureServiceTest {

  @Test
  public void canRetrieveTemperatureData() {
    TemperatureService weatherDotCom = new WeatherDotComTemperatureService();
    int temp = weatherDotCom.temperatureFor("London", DayOfWeek.MONDAY);
    assertThat(temp, is(greaterThan(-20)));
  }
}
