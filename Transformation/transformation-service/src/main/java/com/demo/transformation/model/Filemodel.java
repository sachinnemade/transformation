package com.demo.transformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "filemodel")
public class Filemodel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rowid")
    private int rowId;

    private String moduleid;

    private int fileid;

    private String filetypename;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="moduleid", referencedColumnName="moduleid",insertable = false,updatable = false)
    })
    private Transformationmodule transformationmodule;


    public Transformationmodule getTransformationmodule() {
        return transformationmodule;
    }

    public void setTransformationmodule(Transformationmodule transformationmodule) {
        this.transformationmodule = transformationmodule;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
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
}
