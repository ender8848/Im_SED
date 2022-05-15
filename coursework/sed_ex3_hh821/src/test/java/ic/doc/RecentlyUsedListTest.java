package ic.doc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecentlyUsedListTest {
    RecentlyUsedList<String> recentlyUsedList = new RecentlyUsedList<>();

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(new RecentlyUsedList<String>().isEmpty(), is(true));
    }

    @Test
    public void canAddItems() {
        recentlyUsedList.add("Hello");
        assertThat(recentlyUsedList.isEmpty(), is(false));
    }

    @Test
    public void canRetrieveItems() {
        recentlyUsedList.add("Hello");
        assertThat(recentlyUsedList.retrieve(0), equalTo("Hello"));
    }

    @Test
    public void mostRecentItemShouldBeFirst() {
        recentlyUsedList.add("1");
        recentlyUsedList.add("2");
        assertThat(recentlyUsedList.retrieve(0), equalTo("2"));
    }

    @Test
    public void itemsInListAreUnique() {
        recentlyUsedList.add("1");
        recentlyUsedList.add("2");
        recentlyUsedList.add("1");
        assertThat(recentlyUsedList.size(), is(2));
        assertThat(recentlyUsedList.retrieve(0), equalTo("1"));
    }
}
