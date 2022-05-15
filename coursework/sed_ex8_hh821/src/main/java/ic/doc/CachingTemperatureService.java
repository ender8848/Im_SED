package ic.doc;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.Map;

public class CachingTemperatureService implements TemperatureService {
  private final TemperatureService downstream;
  private final Map<Pair<String, DayOfWeek>, Integer> cache;

  public CachingTemperatureService(TemperatureService downstream) {
    this(downstream, 1000);
  }

  public CachingTemperatureService(TemperatureService downstream, int capacity) {
    this.downstream = downstream;
    this.cache =
        new LinkedHashMap<>() {
          @Override
          protected boolean removeEldestEntry(Map.Entry<Pair<String, DayOfWeek>, Integer> eldest) {
            return size() > capacity;
          }
        };
  }

  @Override
  public int temperatureFor(String place, DayOfWeek dayOfWeek) {
    Pair<String, DayOfWeek> key = new Pair<>(place, dayOfWeek);
    if (!cache.containsKey(key)) {
      cache.put(key, downstream.temperatureFor(place, dayOfWeek));
    }
    return cache.get(key);
  }
}
