package com.InsightIQ.InsightIQ.repository;


import com.InsightIQ.InsightIQ.dto.AveragePriceDto;
import com.InsightIQ.InsightIQ.dto.BrandCountDto;
import com.InsightIQ.InsightIQ.dto.StateCountDto;
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


    @Query("""
     SELECT new com.InsightIQ.InsightIQ.dto.StateCountDto(
             c.state,
             COUNT(c)
             )
    FROM CarSaleEntity c
    GROUP BY c.state
    ORDER BY COUNT(c) DESC
   """)
    List<StateCountDto> getStateWiseCount();

    @Query("""
    SELECT new com.InsightIQ.InsightIQ.dto.AveragePriceDto(
    AVG(c.price)
    )
        FROM CarSaleEntity c
   """)
    AveragePriceDto getAveragePrice();

    @Query("""
            Select new com.InsightIQ.InsightIQ.dto.BrandCountDto(
            c.carBrand,
            Count(c)
            )
            From CarSaleEntity c
            Group By c.carBrand
            Order By Count(c) Desc
           """)
    List<BrandCountDto> getBrandCount();

}
