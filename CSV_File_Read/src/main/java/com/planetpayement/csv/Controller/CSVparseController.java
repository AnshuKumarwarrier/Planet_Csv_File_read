package com.planetpayement.csv.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.planetpayement.csv.Exception.InvalidPhoneNumberException;
import com.planetpayement.csv.Helper.CSVHelper;
import com.planetpayement.csv.Message.ResponseMessage;
import com.planetpayement.csv.model.CSVData;
import com.planetpayement.csv.service.csvFileService;

@RestController
@RequestMapping("/api/v1.1")
public class CSVparseController {
	
	private csvFileService fileService;

    @Autowired
    public CSVparseController(csvFileService fileService) {
        this.fileService = fileService;
    }

	
	@PostMapping("/uploadfile")
	ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		
		if (CSVHelper.hasCSVFormat(file)) {
			
			try {
				
				fileService.save(file);

		        message = "Uploaded the file successfully: " + file.getOriginalFilename();
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}
			catch(Exception e) {
		        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		        System.out.println(e.getMessage());
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			
			
		}
		
	
	}
		 message = "Please upload a csv file!";
		 System.out.println(message);
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	
	
	}
	
	@GetMapping("/{PHONE_NUMBER}")
    public ResponseEntity<Optional<CSVData>> getOrderById(@PathVariable String PHONE_NUMBER) throws InvalidAlgorithmParameterException {
		
		Optional<CSVData> data ;
        try {
        	data = fileService.getOrderById(PHONE_NUMBER);
            
        } 
        
        catch (InvalidPhoneNumberException ex) {
          throw new InvalidPhoneNumberException(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid hone number--> "+PHONE_NUMBER));
        }
        
        catch (NullPointerException e) {
		throw new InvalidAlgorithmParameterException(" data Not available "+PHONE_NUMBER);
		}
        
      return ResponseEntity.ok(data);
        
        
        
    }
	
	
	
	
	
}

