package com.demo.transformation.service;

import com.demo.transformation.iservice.UploadrequestDetailRepository;
import com.demo.transformation.iservice.UploadrequestRepository;
import com.demo.transformation.iservice.iUploadrequestdetailService;
import com.demo.transformation.model.S3UploadFileDetail;
import com.demo.transformation.model.S3UploadSignedDetail;
import com.demo.transformation.model.Uploadrequestdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadrequestdetailService implements iUploadrequestdetailService {

    @Autowired
    UploadrequestDetailRepository uploadrequestDetailRepository;

    @Override
    public void saveuploadrequest(String uploadrequestid, List<S3UploadFileDetail> s3UploadFileDetailList) {

    for(S3UploadFileDetail s3UploadFileDetail : s3UploadFileDetailList){

        Uploadrequestdetails uploadrequestdetails = new Uploadrequestdetails();
        uploadrequestdetails.setRequestid(uploadrequestid);
        uploadrequestdetails.setFileid(String.valueOf(s3UploadFileDetail.getFileid()));
        uploadrequestdetails.setFileContentType(s3UploadFileDetail.getFileContentType());
        uploadrequestdetails.setS3URL(s3UploadFileDetail.getS3URL());
        uploadrequestdetails.setFiletypename(s3UploadFileDetail.getFiletypename());
        uploadrequestdetails.setModuleid(s3UploadFileDetail.getModuleid());
        uploadrequestdetails.setClientfilename(s3UploadFileDetail.getClientfilename());
        uploadrequestDetailRepository.save(uploadrequestdetails);

    }

    }

    @Override
    public void updateuploadrequeststatus(S3UploadSignedDetail s3UploadSignedDetail) {
        String uploadRequestid = s3UploadSignedDetail.getUploadrequestid();

        for(S3UploadFileDetail s3UploadFileDetail : s3UploadSignedDetail.getS3UploadFileDetail()){

            Integer fileid = s3UploadFileDetail.getFileid();
            String uploadStatus = String.valueOf(s3UploadFileDetail.getUploadstatus());
            String uploadStatusRemark = String.valueOf(s3UploadFileDetail.getUploadstatusremark());
            uploadrequestDetailRepository.uploadFileuloadstatus(uploadRequestid, String.valueOf(fileid) ,uploadStatus,uploadStatusRemark);
        }
    }
}
