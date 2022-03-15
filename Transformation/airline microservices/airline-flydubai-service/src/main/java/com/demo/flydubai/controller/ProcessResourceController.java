package com.demo.flydubai.controller;

import java.io.*;
import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.demo.flydubai.model.S3UploadSignedDetail;
import com.demo.flydubai.service.ConfigValues;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@RequestMapping("/v1")
public class ProcessResourceController {

	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ConfigValues configValues;

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${application.bucket.name}")
	private String bucketName;

//	@Value("${spring.profiles.active}")
//	private String activeProfile;

	Logger log=LoggerFactory.getLogger(ProcessResourceController.class);

//
//	public String getActiveProfile() {
//		return activeProfile;
//	}
//
//	public void setActiveProfile(String activeProfile) {
//		this.activeProfile = activeProfile;
//	}

	@GetMapping(value="/transform")
	public ResponseEntity<String> transform() {

		String s3Credentials = configValues.getAccesskey() + "##" + configValues.getSecretkey() + "##" + configValues.getToken();
		return new ResponseEntity<String>("Config values are " + s3Credentials,HttpStatus.OK );//+ ". Active profile is "+ activeProfile,HttpStatus.OK);
		
	}

	@PostMapping(value="/transformation")
	public ResponseEntity<String> transformation() throws IOException {
			//@RequestBody S3UploadSignedDetail s3UploadSignedDetail

		S3Object data = amazonS3.getObject(bucketName, "AutoRecommendSheet_0319_before.xlsx");
		S3ObjectInputStream objectContent = data.getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectContent);
		ByteArrayResource resource = new ByteArrayResource(bytes);
		objectContent.close();

		Workbook workbook = null;
		InputStream targetStream = new ByteArrayInputStream(bytes);
		workbook = new XSSFWorkbook(targetStream);

		workbook.createSheet("New Sheet");

		File outputFile = File.createTempFile("temp", ".xlsx");
		try (FileOutputStream fos = new FileOutputStream(outputFile)) {
			workbook.write(fos);
		}
		amazonS3.putObject(bucketName, UUID.randomUUID() + outputFile.getName(), outputFile);


		//return new ResponseEntity<String>("transformation called with module" + s3UploadSignedDetail.getModulename(),HttpStatus.OK);
		return new ResponseEntity<String>("transformation called",HttpStatus.OK);

//		log.info("Fly dubai service content - " + s3UploadSignedDetail.getModulename());
//		String s3Credentials = configValues.getAccesskey() + "##" + configValues.getSecretkey() + "##" + configValues.getToken();
//		return new ResponseEntity<String>("Body" + s3UploadSignedDetail.getModulename() + "Config values are " + s3Credentials,HttpStatus.OK );//+ ". Active profile is "+ activeProfile,HttpStatus.OK);

	}

	@GetMapping(value="/token")
	public ResponseEntity<String> token() {

		return new ResponseEntity<String>("Config value of token is " + configValues.getToken(),HttpStatus.OK);

	}
	
	

}
