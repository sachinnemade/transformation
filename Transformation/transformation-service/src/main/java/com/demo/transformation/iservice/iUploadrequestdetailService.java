package com.demo.transformation.iservice;

import com.demo.transformation.model.S3UploadFileDetail;
import com.demo.transformation.model.S3UploadSignedDetail;

import java.util.List;

public interface iUploadrequestdetailService {

    public void saveuploadrequest(String uploadrequestid, List<S3UploadFileDetail> s3UploadFileDetailList);

    public void updateuploadrequeststatus(S3UploadSignedDetail s3UploadSignedDetail);
}
