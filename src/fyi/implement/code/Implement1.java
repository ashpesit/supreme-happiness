package fyi.implement.code;

import java.util.Comparator;

public class Implement1 {

    class Event{
        String eventId;
        String productId;
        int time;

        public Event(String eventId, String productId, int time) {
            this.eventId = eventId;
            this.productId = productId;
            this.time = time;
        }
    }
    class ProductCount{
        String productId;
        int count;
        int time;

        public ProductCount(String productId, int count, int time) {
            this.productId = productId;
            this.count = count;
            this.time = time;
        }
    }
    Comparator<ProductCount> comparator= (o1, o2) -> 0;

    public static void main(String[] args) {

    }



}
