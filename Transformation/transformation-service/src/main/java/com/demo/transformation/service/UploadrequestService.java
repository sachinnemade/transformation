package com.demo.transformation.service;

import com.demo.transformation.iservice.UploadrequestRepository;
import com.demo.transformation.iservice.iUploadrequestService;
import com.demo.transformation.model.S3UploadSignedDetail;
import com.demo.transformation.model.Uploadrequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UploadrequestService implements iUploadrequestService {

    @Autowired
    UploadrequestRepository uploadrequestRepository;

    @Override
    public void saveuploadrequest(S3UploadSignedDetail s3UploadSignedDetail) {
        Uploadrequest uploadrequest = new Uploadrequest();
        uploadrequest.setRequestid(s3UploadSignedDetail.getUploadrequestid());
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));

        uploadrequest.setUploadrequestdate(new Date());
        uploadrequestRepository.save(uploadrequest);
    }
}
