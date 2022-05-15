package Q3;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;

public class BoxOfficeTest {

  static final Show LION_KING =
      new Show("The Lion King", 44.00);

  static final Show HAMILTON =
      new Show("Hamilton", 80.00);

  static final Customer SALLY = new Customer("Sally Davies");
  static final Customer TOM = new Customer("Thomas Williams");

  // write your tests here

  public JUnitRuleMockery context = new JUnitRuleMockery();
  Theatre theatre = context.mock(Theatre.class);
  Payments payments = context.mock(Payments.class);
  WaitingList waitingList = context.mock(WaitingList.class);
  BoxOffice boxOffice = new BoxOffice(theatre, payments, waitingList);

  @Test
  public void bookTicketsIfAvailable() {
    context.checking(new Expectations() {{
      exactly(1).of(theatre).checkAvailable(LION_KING, 4);will(returnValue(true));
      exactly(1).of(payments).pay(LION_KING.price()*4, SALLY);
      exactly(1).of(theatre).confirm(LION_KING,4);
    }});

    boxOffice.bookTickets(LION_KING, 4, SALLY);
  }

  @Test
  public void addToWaitingListIfNoTicketsAvailable() {
    context.checking(new Expectations() {{
      exactly(1).of(theatre).checkAvailable(HAMILTON,2);will(returnValue(false));
      exactly(1).of(waitingList).add(TOM, HAMILTON, 2);
    }});

    boxOffice.bookTickets(HAMILTON,2,TOM);
  }

  @Test
  public void returnTicketsTriggersWaitingListPeopleBuyTickets() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(waitingList).anyoneWaiting(HAMILTON, 4);
            exactly(1).of(payments).pay(HAMILTON.price()*2, TOM);
            exactly(1).of(theatre).checkAvailable(HAMILTON,2); will(returnValue(true));
            ignoring(theatre);
          }
        });
    boxOffice.returnTickets(HAMILTON, 4);
    boxOffice.bookTickets(HAMILTON, 2 ,TOM);
  }
}
