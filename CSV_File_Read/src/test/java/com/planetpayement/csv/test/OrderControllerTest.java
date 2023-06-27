package com.planetpayement.csv.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.planetpayement.csv.repository.csvFileRepo;
import com.planetpayement.csv.service.csvFileService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;
	 
	 @Autowired
	    private csvFileRepo orderRepository;
	 
	 @Autowired
	 private csvFileService fileService;

	    @Test
	    public void testUploadCSVFile_Success() throws Exception {
	        // Create a test CSV file
	        InputStream inputStream = new FileInputStream("E://Microservices Workspace/file.csv");
	        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", inputStream);

	        // Perform the upload
	        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1.1/uploadfile")
	                .file(file))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("File uploaded and processed successfully."));
	    }

	    @Test
	    public void testUploadCSVFile_Failure() throws Exception {
	        // Create a test CSV file
	        InputStream inputStream = new FileInputStream("E://Microservices Workspace/file.csv");
	        MockMultipartFile file = new MockMultipartFile("file", "file.csv", "text/csv", inputStream);

	        // Perform the upload
	        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1.1/uploadfile")
	                .file(file))
	                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
	                .andExpect(MockMvcResultMatchers.content().string("Failed to process the file."));
	    }
	    
	    /*
	    @Test
	    public void testUploadCSVFile_InvalidPhoneNumber() throws Exception {
	        // Create a test CSV file with an invalid phone number
	        InputStream inputStream = new FileInputStream("E://Microservices Workspacetest/file.csv");
	        MockMultipartFile file = new MockMultipartFile("file", "file.csv", "text/csv", inputStream);

	        // Perform the upload
	        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1.1/uploadfile")
	                .file(file))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("File uploaded and processed successfully."));

	        // Assert that there is one order with an invalid country
	        String invalidCountryCount = orderRepository.countByCountry("Invalid");
	        assertEquals(1, invalidCountryCount, "There should be one order with an invalid country");
	        
	     // Assert that there is one order with an invalid phone number
	        String invalidPhoneNumberCount = orderRepository.countByPhoneNumberContaining("Invalid");
	        assertEquals(1, invalidPhoneNumberCount, "There should be one order with an invalid phone number");
	    }
	    
	    */
	    
	    @Test
	    void testCountryAvailability_Positive() {
	        String availableCountry = "Cameroon";
	        boolean isAvailable = isCountryAvailable(availableCountry);
	        assertTrue(isAvailable, "Country should be available");
	    }

	    @Test
	    void testCountryAvailability_Negative() {
	        String unavailableCountry = "InvalidCountry";
	        boolean isAvailable = isCountryAvailable(unavailableCountry);
	        assertFalse(isAvailable, "Country should not be available");
	    }

	    private boolean isCountryAvailable(String country) {
	    	// Assuming you have a countryService that provides the availability check
	        return fileService.isCountryAvailable(country);
	    }
	

}
