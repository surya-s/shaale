package com.shaale.rms.controller;

import com.shaale.rms.dao.PaymentUpdateDAO;
import com.shaale.rms.entity.Payment;
import com.shaale.rms.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentsRepository paymentsRepo;

    @Autowired
    public void setPaymentsRepo(PaymentsRepository paymentsRepo) {
        this.paymentsRepo = paymentsRepo;
    }

    @GetMapping
    public List<Payment> getAll() {
        return paymentsRepo.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public List<Payment> updatePayments(final @RequestBody List<PaymentUpdateDAO> list) {
        List<Payment> toDelete = list.stream().filter(o -> o.getAction() == PaymentUpdateDAO.Action.DELETE)
                .map(PaymentUpdateDAO::getData).collect(Collectors.toList());
        List<Payment> toUpdate = list.stream().filter(o -> o.getAction() == PaymentUpdateDAO.Action.UPDATE)
                .map(PaymentUpdateDAO::getData).collect(Collectors.toList());

        List<Payment> result = new ArrayList<>();

        if(!toDelete.isEmpty()){
            paymentsRepo.deleteInBatch(toDelete);
        }
        if(!toUpdate.isEmpty()){
            result = paymentsRepo.saveAll(toUpdate);
        }

        return result;
    }
}
