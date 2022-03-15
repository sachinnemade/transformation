package com.demo.transformation.iservice;

import com.demo.transformation.model.Uploadrequestdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

public interface UploadrequestDetailRepository extends JpaRepository<Uploadrequestdetails, Integer> {

    @Transactional
    @Modifying
    @Query("update Uploadrequestdetails ed set ed.uploadstatus = :status,ed.uploadstatusremark = :statusRemark where ed.fileid = :fileid and ed.requestid = :requestid ")
    int uploadFileuloadstatus(@Param("requestid") String requestid, @Param("fileid")  String fileid,  @Param("status") String status,@Param("statusRemark") String statusRemark);
}
