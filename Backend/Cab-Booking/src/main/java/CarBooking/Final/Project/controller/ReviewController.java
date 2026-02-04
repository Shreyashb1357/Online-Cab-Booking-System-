package CarBooking.Final.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import CarBooking.Final.Project.entity.Review;
import CarBooking.Final.Project.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public Review addReview(@RequestParam Integer rideId, @RequestParam Integer rating, @RequestParam(required = false) String comment) {
        return reviewService.addReview(rideId, rating, comment);
    }
}
