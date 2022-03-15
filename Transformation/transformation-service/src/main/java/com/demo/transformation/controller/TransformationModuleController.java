package com.demo.transformation.controller;

import com.amazonaws.HttpMethod;
import com.demo.transformation.model.*;
import com.demo.transformation.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class TransformationModuleController {

    @Autowired
    private TransformationModuleService transformationModuleService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private StorageService service;

    @Autowired
    UploadrequestService uploadrequestService;

    @Autowired
    UploadrequestdetailService uploadrequestdetailService;

    Logger log= LoggerFactory.getLogger(UtilityResourceController.class);

    @GetMapping(value="/modules")
    public ResponseEntity<?> getAllmodules(HttpServletRequest req) {
        List<Transformationmodule> moduleDetails= transformationModuleService.getTransformationModules();
        return new ResponseEntity<List<Transformationmodule>>(moduleDetails, HttpStatus.FOUND);
        //return moduleDetails;
    }

    @GetMapping(value="/testcall")
    public ResponseEntity<String> testcall(HttpServletRequest req) {

        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization", req.getHeader("Authorization"));
        headers.add("content-type",MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpHeaders=new HttpEntity<String>("parameters",headers);

        log.info("calling flydubai service");
        ResponseEntity<String> utilEntity=
                restTemplate.exchange("http://flydubai-service:9095/v1/transform",
                        org.springframework.http.HttpMethod.GET,httpHeaders,String.class);

//        return new ResponseEntity<String>("Call-1", HttpStatus.FOUND);
        return new ResponseEntity<String>(utilEntity.getBody(), HttpStatus.FOUND);
    }


    @GetMapping("/getpresignedurl/{moduleid}")
    public ResponseEntity<S3UploadSignedDetail> getPresignedURLForUpload(@PathVariable String moduleid) {

        Transformationmodule transformationmodule = transformationModuleService.getTransformationModule(moduleid);
        String foldername = transformationmodule.getModulename();
        DateFormat formatter = new SimpleDateFormat("HHmmssSSS");

        String subfoldername = formatter.format(new Date(System.currentTimeMillis()));

        S3UploadSignedDetail s3UploadSignedDetail = new S3UploadSignedDetail();
        s3UploadSignedDetail.setAccount(transformationmodule.getAccount());
        s3UploadSignedDetail.setCms(transformationmodule.getCms());
        s3UploadSignedDetail.setModuleid(transformationmodule.getModuleid());
        s3UploadSignedDetail.setModulename(transformationmodule.getModulename());

        List<S3UploadFileDetail> s3UploadFileDetails = new ArrayList<S3UploadFileDetail>();

        for(Filemodel filemodel : transformationmodule.getFiledetails()){
            S3UploadFileDetail s3UploadFileDetail = new S3UploadFileDetail();
            s3UploadFileDetail.setFileid(filemodel.getFileid());
            s3UploadFileDetail.setFiletypename(filemodel.getFiletypename());
            URL urltoUpload = service.generatePresignedUrl
                    (foldername + "/" + subfoldername + "/" + filemodel.getFiletypename() + filemodel.getFileid(), HttpMethod.POST,"");

            s3UploadFileDetail.setS3URL(urltoUpload.toString());
            s3UploadFileDetails.add(s3UploadFileDetail);
        }
        s3UploadSignedDetail.setS3UploadFileDetail(s3UploadFileDetails);
        return new ResponseEntity<S3UploadSignedDetail>(s3UploadSignedDetail, HttpStatus.OK);
    }

    @RequestMapping (value="uploadRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<S3UploadSignedDetail> getPresignedURLForFilesUpload
            (@RequestBody List<S3UploadFileDetail> s3UploadFileDetailsFromClient ) {

        String moduleid = s3UploadFileDetailsFromClient.get(0).getModuleid();

        Transformationmodule transformationmodule = transformationModuleService.getTransformationModule(moduleid);
        String foldername = transformationmodule.getModulename();
        DateFormat formatter = new SimpleDateFormat("HHmmssSSS");

        String subfoldername = formatter.format(new Date(System.currentTimeMillis()));

        S3UploadSignedDetail s3UploadSignedDetail = new S3UploadSignedDetail();
        s3UploadSignedDetail.setAccount(transformationmodule.getAccount());
        s3UploadSignedDetail.setCms(transformationmodule.getCms());
        s3UploadSignedDetail.setModuleid(transformationmodule.getModuleid());
        s3UploadSignedDetail.setModulename(transformationmodule.getModulename());
        s3UploadSignedDetail.setUploadrequestid("Job-" + moduleid + "-" + foldername + "-" + subfoldername);

        List<S3UploadFileDetail> s3UploadFileDetails = new ArrayList<S3UploadFileDetail>();

        int fileCounter = -1;
        for(Filemodel filemodel : transformationmodule.getFiledetails()){
            S3UploadFileDetail s3UploadFileDetail = new S3UploadFileDetail();
            s3UploadFileDetail.setFileid(filemodel.getFileid());
            s3UploadFileDetail.setFiletypename(filemodel.getFiletypename());
            s3UploadFileDetail.setModuleid(moduleid);
            S3UploadFileDetail s3UploadFileDetailFromClient = s3UploadFileDetailsFromClient.get(++fileCounter);
            String contentype = service.getContenttype(s3UploadFileDetailFromClient.getClientfilename());
            s3UploadFileDetail.setFileContentType(contentype);
            String fileName = s3UploadFileDetailFromClient.getClientfilename();
            s3UploadFileDetail.setClientfilename(fileName);

            URL urltoUpload = service.generatePresignedUrl
                    (foldername + "/" + subfoldername + "/"
                            + filemodel.getFileid() + "_" + filemodel.getFiletypename() + "."
                            + fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()), HttpMethod.POST,
                            s3UploadFileDetailFromClient.getClientfilename());

            s3UploadFileDetail.setS3URL(urltoUpload.toString());
            s3UploadFileDetails.add(s3UploadFileDetail);
        }
        s3UploadFileDetails.sort(Comparator.comparing(S3UploadFileDetail::getFileid));
        s3UploadSignedDetail.setS3UploadFileDetail(s3UploadFileDetails);
        uploadrequestService.saveuploadrequest(s3UploadSignedDetail);
        uploadrequestdetailService.saveuploadrequest(s3UploadSignedDetail.getUploadrequestid(), s3UploadFileDetails);
        return new ResponseEntity<S3UploadSignedDetail>(s3UploadSignedDetail, HttpStatus.OK);
    }


    @RequestMapping (value="process", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getPresignedURLForFilesUpload
            (@RequestBody S3UploadSignedDetail s3UploadSignedDetail,HttpServletRequest req ) {

        uploadrequestdetailService.updateuploadrequeststatus(s3UploadSignedDetail);
        //return new ResponseEntity<String>("JOB is submitted for request " + s3UploadSignedDetail.getUploadrequestid(), HttpStatus.OK);

        log.info("calling fly dubai service");
        //Get method calling test
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization", req.getHeader("Authorization"));
        headers.add("content-type",MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpHeaders=new HttpEntity<String>("parameters",headers);
//        ResponseEntity<String> utilEntity=
//                restTemplate.exchange("http://flydubai-service:9095/v1/transform",
//                        org.springframework.http.HttpMethod.GET,httpHeaders,String.class);
        //return new ResponseEntity<String>(utilEntity.getBody(), HttpStatus.FOUND);
        ResponseEntity<String> utilEntityresp=
                restTemplate.postForEntity("http://flydubai-service:9095/v1/transformation",s3UploadSignedDetail,String.class);

        //return new ResponseEntity<String>(utilEntityresp.getBody(), HttpStatus.FOUND);
        return new ResponseEntity<String>("utilEntityresp.getBody()" + utilEntityresp, HttpStatus.FOUND);
    }
}
