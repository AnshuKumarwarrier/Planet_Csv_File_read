package com.planetpayement.csv.service;


import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.planetpayement.csv.model.CSVData;



public interface csvFileService {

	void save(MultipartFile file) throws IOException;

	Optional<CSVData> getOrderById(String pHONE_NUMBER);

	boolean isCountryAvailable(String country);


}
