package com.demo.transformation.model;

public class S3UploadFileDetail {

    private int fileid;
    private String filetypename;
    private String s3URL;

    private String clientfilename;
    private String fileContentType;
    private String moduleid;
    private String uploadstatus;
    private String uploadstatusremark;

    public String getUploadstatus() {
        return uploadstatus;
    }

    public void setUploadstatus(String uploadstatus) {
        this.uploadstatus = uploadstatus;
    }

    public String getUploadstatusremark() {
        return uploadstatusremark;
    }

    public void setUploadstatusremark(String uploadstatusremark) {
        this.uploadstatusremark = uploadstatusremark;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getClientfilename() {
        return clientfilename;
    }

    public void setClientfilename(String clientfilename) {
        this.clientfilename = clientfilename;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    public String getFiletypename() {
        return filetypename;
    }

    public void setFiletypename(String filetypename) {
        this.filetypename = filetypename;
    }

    public String getS3URL() {
        return s3URL;
    }

    public void setS3URL(String s3URL) {
        this.s3URL = s3URL;
    }
}
