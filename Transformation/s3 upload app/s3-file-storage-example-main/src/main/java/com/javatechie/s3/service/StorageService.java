package com.javatechie.s3.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public URL generatePresignedUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours

        GeneratePresignedUrlRequest request =
                new GeneratePresignedUrlRequest(bucketName, "folder1/"+String.valueOf(fileName)).withMethod(HttpMethod.PUT);
        request.setExpiration(calendar.getTime());
        request.setContentType(URLConnection.getFileNameMap().getContentTypeFor(fileName));
        return s3Client.generatePresignedUrl(request);
        //return s3Client.generatePresignedUrl(bucketName, fileName, calendar.getTime(), httpMethod).toString();
    }

//    public URL getPreSignedUrl(long attachmentId, HttpMethod method, boolean unaccelerated) {
//        AmazonS3                    client  = new AmazonS3Client(credentials);
//        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, String.valueOf(attachmentId), method);
//
//        request.setExpiration(new Date(System.currentTimeMillis() + DURATION));
//        request.setContentType("application/octet-stream");
//        if (unaccelerated) {
//            client.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
//        } else {
//            client.setS3ClientOptions(S3ClientOptions.builder().setAccelerateModeEnabled(true).build());
//        }
//        return client.generatePresignedUrl(request);
//    }

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            //log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
