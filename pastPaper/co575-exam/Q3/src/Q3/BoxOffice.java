package Q3;

public class BoxOffice {

    private final Theatre theatre;
    private final Payments payments;
    private final WaitingList waitingList;

    BoxOffice(Theatre theatre, Payments payments, WaitingList waitingList) {
        this.theatre = theatre;
        this.payments = payments;
        this.waitingList = waitingList;
    }

    public void bookTickets(Show show, Integer num, Customer customer) {
        if (theatre.checkAvailable(show, num)) {
            payments.pay(show.price()*num, customer);
            theatre.confirm(show, num);
        } else {
            waitingList.add(customer,show,num);
        }
    }


    public void returnTickets(Show show, int num) {
        waitingList.anyoneWaiting(show, num);
    }
}
