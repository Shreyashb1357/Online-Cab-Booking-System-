package CarBooking.Final.Project.service;

import CarBooking.Final.Project.entity.Review;

public interface ReviewService {

    Review addReview(Integer rideId, Integer rating, String comment);
}
