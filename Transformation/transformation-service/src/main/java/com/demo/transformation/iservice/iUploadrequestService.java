package com.demo.transformation.iservice;

import com.demo.transformation.model.S3UploadSignedDetail;
import com.demo.transformation.model.Transformationmodule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface iUploadrequestService {
    public void saveuploadrequest(S3UploadSignedDetail s3UploadSignedDetail);
}
