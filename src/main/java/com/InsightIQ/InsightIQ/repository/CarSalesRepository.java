package com.InsightIQ.InsightIQ.repository;


import com.InsightIQ.InsightIQ.entity.CarSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarSalesRepository extends JpaRepository<CarSaleEntity,Long>{



}
