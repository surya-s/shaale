package com.shaale.rms.repository;

import com.shaale.rms.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payment, Integer> {

}
