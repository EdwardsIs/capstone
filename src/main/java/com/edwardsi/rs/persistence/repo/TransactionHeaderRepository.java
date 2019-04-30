package com.edwardsi.rs.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edwardsi.rs.persistence.entities.TransactionHeader;

@Repository
public interface TransactionHeaderRepository extends JpaRepository<TransactionHeader, Long>{

}
