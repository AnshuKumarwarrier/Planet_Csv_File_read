package com.planetpayement.csv.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.planetpayement.csv.Exception.InvalidFileException;
import com.planetpayement.csv.Exception.InvalidPhoneNumberException;
import com.planetpayement.csv.Helper.CSVHelper;
import com.planetpayement.csv.model.CSVData;
import com.planetpayement.csv.repository.csvFileRepo;


@Service
public class csvFileServiceImpl implements csvFileService {

	private final csvFileRepo fileRepo;
	public csvFileServiceImpl(csvFileRepo fileRepo) {
		this.fileRepo = fileRepo;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		try {
		      List<CSVData> data = CSVHelper.csvTodata(file.getInputStream());
		      fileRepo.saveAll(data);
		    } catch (InvalidFileException e) {
		      throw new InvalidFileException("fail to store csv data: " + e.getMessage());
		    }
		
	}

	@Override
	public Optional<CSVData> getOrderById(String pHONE_NUMBER) {
		// TODO Auto-generated method stub
		
		String  numericPhoneNumber =  pHONE_NUMBER.replaceAll("[^\\d]", "").replaceAll(" ", "");
		
		return Optional.ofNullable(fileRepo.findById(Long.parseLong(numericPhoneNumber)).
				 orElseThrow(() -> new InvalidPhoneNumberException ("Order not found with ID: " + pHONE_NUMBER)));
	}

	@Override
	public boolean isCountryAvailable(String country) {
		// TODO Auto-generated method stub
		 // Implement the logic to check the availability of the country
        // Example: Check if the country exists in a database or a list of available countries
        List<String> availableCountries = Arrays.asList("Cameroon", "Ethiopia", "Morocco", "Mozambique", "Uganda");
        return availableCountries.contains(country);
	}
	
	

	
	

}
