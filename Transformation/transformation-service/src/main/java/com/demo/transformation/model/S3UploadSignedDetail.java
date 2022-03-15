package com.demo.transformation.model;

import java.util.ArrayList;
import java.util.List;

public class S3UploadSignedDetail {

    private String moduleid;
    private String cms;
    private String account;
    private String modulename;
    private String uploadrequestid;

    public String getUploadrequestid() {
        return uploadrequestid;
    }

    public void setUploadrequestid(String uploadrequestid) {
        this.uploadrequestid = uploadrequestid;
    }

    List<S3UploadFileDetail>  s3UploadFileDetail;

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getCms() {
        return cms;
    }

    public void setCms(String cms) {
        this.cms = cms;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public List<S3UploadFileDetail> getS3UploadFileDetail() {
        return s3UploadFileDetail;
    }

    public void setS3UploadFileDetail(List<S3UploadFileDetail> s3UploadFileDetail) {
        this.s3UploadFileDetail = s3UploadFileDetail;
    }
}

