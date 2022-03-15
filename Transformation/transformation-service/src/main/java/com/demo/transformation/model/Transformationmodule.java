package com.demo.transformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "transformationmodule")
public class Transformationmodule  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rowid")
    private int rowId;

    private String moduleid;
    private String cms;
    private String account;
    private String modulename;



    @OneToMany(mappedBy = "transformationmodule")
    private Set<Filemodel> filedetails;

    public Set<Filemodel> getFiledetails() {
        return filedetails;
    }

    public void setFiledetails(Set<Filemodel> filedetails) {
        this.filedetails = filedetails;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
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

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }
}
