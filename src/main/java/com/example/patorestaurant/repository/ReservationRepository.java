package com.example.patorestaurant.repository;

import com.example.patorestaurant.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findAllByTimeLike(String time);
}
