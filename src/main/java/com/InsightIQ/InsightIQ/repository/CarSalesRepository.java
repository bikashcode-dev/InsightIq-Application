package com.InsightIQ.InsightIQ.repository;


import com.InsightIQ.InsightIQ.dto.YearlyCountDto;
import com.InsightIQ.InsightIQ.entity.CarSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarSalesRepository extends JpaRepository<CarSaleEntity, Long> {
    boolean existsByCarNumber(String carNumber);

    @Query("""
    Select new com.InsightIQ.InsightIQ.dto.YearlyCountDto(
        c.year,
        Count(c)
    )
    From CarSaleEntity c
    Group By c.year
    Order By c.year
""")
    List<YearlyCountDto> getYearlyCount();
}
