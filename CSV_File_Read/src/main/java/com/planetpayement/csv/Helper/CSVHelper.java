package com.planetpayement.csv.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.planetpayement.csv.model.CSVData;

public class CSVHelper {

	public static String TYPE = "multipart/form-data";
	static String[] HEADERs = { "id", "phone_number", "email", "parcel_weight" };

	private static final String CAMEROON_REGEX = "\\(237\\)\\ ?[2368]\\d{7,8}$";
	private static final String ETHIOPIA_REGEX = "\\(251\\)\\ ?[1-59]\\d{8}$";
	private static final String MOROCCO_REGEX = "\\(212\\)\\ ?[5-9]\\d{8}$";
	private static final String MOZAMBIQUE_REGEX = "\\(258\\)\\ ?[28]\\d{7,8}$";
	private static final String UGANDA_REGEX = "\\(256\\)\\ ?\\d{9}$";

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<CSVData> csvTodata(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<CSVData> csvdata = new ArrayList<CSVData>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {

				String phoneNumber = csvRecord.get("phoneNumber");
				String country = determineCountry(phoneNumber);
				CSVData data = new CSVData(

						Long.parseLong(csvRecord.get("id")), phoneNumber, csvRecord.get("email"),
						Double.parseDouble(csvRecord.get("parcelWeight")), country

				);

				csvdata.add(data);
			}

			return csvdata;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	private static String determineCountry(String phoneNumber) {
		Pattern cameroonPattern = Pattern.compile(CAMEROON_REGEX);
		Pattern ethiopiaPattern = Pattern.compile(ETHIOPIA_REGEX);
		Pattern moroccoPattern = Pattern.compile(MOROCCO_REGEX);
		Pattern mozambiquePattern = Pattern.compile(MOZAMBIQUE_REGEX);
		Pattern ugandaPattern = Pattern.compile(UGANDA_REGEX);

		if (cameroonPattern.matcher(phoneNumber).matches()) {
			return "Cameroon";
		} else if (ethiopiaPattern.matcher(phoneNumber).matches()) {
			return "Ethiopia";
		} else if (moroccoPattern.matcher(phoneNumber).matches()) {
			return "Morocco";
		} else if (mozambiquePattern.matcher(phoneNumber).matches()) {
			return "Mozambique";
		} else if (ugandaPattern.matcher(phoneNumber).matches()) {
			return "Uganda";
		}

		return "Unknown";
	}
}
