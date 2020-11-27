package com.example.startwithofficialdocumentation.common;

import com.example.startwithofficialdocumentation.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author XieDuoLiang
 * @date 2020/11/11 下午5:28
 */
@Component
@Slf4j
@Order(200)
public class TransactionTestRunner implements CommandLineRunner {

    private final BookingService bookingService;

    public TransactionTestRunner(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) throws Exception {
        /*bookingService.book("Alice", "Bob", "Carol");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "First booking should work with no problem");
        log.info("Alice, Bob and Carol have been booked");
        try {
            bookingService.book("Chris", "Samuel");
        } catch (RuntimeException e) {
            log.info("v--- The following exception is expect because 'Samuel' is too " +
                    "big for the DB ---v");
            log.error(e.getMessage());
        }

        for (String person : bookingService.findAllBookings()) {
            log.info("So far, " + person + " is booked.");
        }
        log.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +
                "and Chris was rolled back in the same TX");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "'Samuel' should have triggered a rollback");

        try {
            bookingService.book("Buddy", null);
        } catch (RuntimeException e) {
            log.info("v--- The following exception is expect because null is not " +
                    "valid for the DB ---v");
            log.error(e.getMessage());
        }

        for (String person : bookingService.findAllBookings()) {
            log.info("So far, " + person + " is booked.");
        }
        log.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
                "Buddy was rolled back in the same TX");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "'null' should have triggered a rollback");*/
    }
}
