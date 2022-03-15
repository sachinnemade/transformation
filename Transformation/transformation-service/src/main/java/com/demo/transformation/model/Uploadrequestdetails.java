package com.demo.transformation.model;

import javax.persistence.*;

@Entity
@Table(name = "uploadrequestdetails")
public class Uploadrequestdetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rowid")
    private int rowId;

    private String requestid;
    private String fileid;
    private String filetypename;

    @Column(length = 500)
    private String s3URL;
    private String fileContentType;
    private String uploadstatus;
    private String uploadstatusremark;
    private String moduleid;

    private String clientfilename;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getClientfilename() {
        return clientfilename;
    }

    public void setClientfilename(String clientfilename) {
        this.clientfilename = clientfilename;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
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

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

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
}
