package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CachingTemperatureServiceTest {
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  TemperatureService downstream = context.mock(TemperatureService.class);

  @Test
  public void preventMultipleRequestDownstreamForSameParameters() {
    TemperatureService temperatureService = new CachingTemperatureService(downstream, 1);

    context.checking(
        new Expectations() {
          {
            exactly(1).of(downstream).temperatureFor("London", DayOfWeek.MONDAY);
            will(returnValue(11));
          }
        });

    assertThat(temperatureService.temperatureFor("London", DayOfWeek.MONDAY), is(11));
    assertThat(temperatureService.temperatureFor("London", DayOfWeek.MONDAY), is(11));
  }

  @Test
  public void evictEldestMembersWhenCacheReachesCapacity() {
    TemperatureService temperatureService = new CachingTemperatureService(downstream, 1);

    context.checking(
        new Expectations() {
          {
            exactly(2).of(downstream).temperatureFor("London", DayOfWeek.MONDAY);
            will(returnValue(11));
            exactly(1).of(downstream).temperatureFor("London", DayOfWeek.TUESDAY);
            will(returnValue(11));
          }
        });

    temperatureService.temperatureFor("London", DayOfWeek.MONDAY);
    temperatureService.temperatureFor("London", DayOfWeek.TUESDAY);
    temperatureService.temperatureFor("London", DayOfWeek.MONDAY);
  }
}
