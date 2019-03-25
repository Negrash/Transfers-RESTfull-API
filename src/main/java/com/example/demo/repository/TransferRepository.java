package com.example.demo.repository;

import com.example.demo.model.Transfer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Integer> {
    List<Transfer> findAll();
}
