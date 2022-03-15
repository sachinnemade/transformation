package com.demo.transformation.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "uploadrequest")
public class Uploadrequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rowid")
    private int rowId;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    private String requestid;

    private Date uploadrequestdate;

    public Date getUploadrequestdate() {
        return uploadrequestdate;
    }

    public void setUploadrequestdate(Date uploadrequestdate) {
        this.uploadrequestdate = uploadrequestdate;
    }
}
