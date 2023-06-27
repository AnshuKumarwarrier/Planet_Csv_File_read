package com.planetpayement.csv.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.planetpayement.csv.model.CSVData;

@Repository
@EnableJpaRepositories
public interface csvFileRepo extends JpaRepository<CSVData, Long> {

	/*
	 @Query("SELECT COUNT(COUNTRY) FROM Orderdata o WHERE o.COUNTRY = :COUNTRY")
	    String countByCountry(@Param("COUNTRY") String COUNTRY);

	 @Query("SELECT COUNT(PHONE_NUMBER) FROM Orderdata o WHERE o.PHONE_NUMBER LIKE %:PHONE_NUMBER%")
	 String countByPhoneNumberContaining( @Param("phoneNumber") String phoneNumber);
*/
	 
	

}
