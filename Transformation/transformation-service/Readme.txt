-------------------- FIRST REQUEST to get signed URLs of client files based on file names --------------------

POST
localhost:8082/v1/uploadRequest

http://localhost:8083/transformationservice/v1/uploadRequest
Headers
Authorization Bearer 9cb8dc8a-7a2f-4c4e-93b4-14e36aaeda6e
Content-Type application/json

Body (raw)
 [
        {
            "fileid": 1,
            "filetypename": "Configuration",
            "clientfilename":"abc.xlsx",
            "moduleid": "101"


        },
        {
            "fileid": 2,
            "filetypename": "Programming Grid",
            "clientfilename":"xyz.xls",
            "moduleid": "101"
        }
 ]
 Response
 {
     "moduleid": "101",
     "cms": "DCMS",
     "account": "Gulf",
     "modulename": "Gulf",
     "uploadrequestid": "Job-101-Gulf-174619052",
     "s3UploadFileDetail": [
         {
             "fileid": 1,
             "filetypename": "Configuration",
             "s3URL": "https://transformation-files.s3.amazonaws.com/Gulf/174619052/1_Configuration.xlsx?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220203T121619Z&X-Amz-SignedHeaders=content-type%3Bhost&X-Amz-Expires=86399&X-Amz-Credential=AKIAVF5MBF6ACCHMQW4S%2F20220203%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=27f0c2e48484f6a27c3460066b8811845b7350a1d57ff7cda05bf999ae4475e2",
             "clientfilename": "abc.xlsx",
             "fileContentType": "vnd.ms-excel",
             "moduleid": "101"
         },
         {
             "fileid": 2,
             "filetypename": "Programming Grid",
             "s3URL": "https://transformation-files.s3.amazonaws.com/Gulf/174619052/2_Programming%20Grid.xls?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220203T121619Z&X-Amz-SignedHeaders=content-type%3Bhost&X-Amz-Expires=86400&X-Amz-Credential=AKIAVF5MBF6ACCHMQW4S%2F20220203%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=0aec98f5e4d29535eda153a5add843cfbb267fb9a1b6ce2bd10648b681ef18a9",
             "clientfilename": "xyz.xls",
             "fileContentType": "application/vnd.ms-excel",
             "moduleid": "101"
         }
     ]
 }

-------------------- Second Request - Call presigned URL --------------------
PUT
https://transformation-files.s3.amazonaws.com/Gulf/173112569/2_Programming%20Grid.xls?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220203T120112Z&X-Amz-SignedHeaders=content-type%3Bhost&X-Amz-Expires=86400&X-Amz-Credential=AKIAVF5MBF6ACCHMQW4S%2F20220203%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=d937e52ddc34396198551257f89085b3b2d19438fd4b639a70a987525d268571
Content-type-{from json}

Body
select file of type eg abc.xls will be uploaded as 2_Programming Grid.xls

--------------------- Third Request - update the upload status and Process --------------
Post
content-type = application/json
url localhost:8082/v1/process
http://localhost:8083/transformationservice/v1/process

{
    "moduleid": "101",
    "cms": "DCMS",
    "account": "Gulf",
    "modulename": "Gulf",
    "uploadrequestid": "Job-101-Gulf-202511020",
    "s3UploadFileDetail": [
        {
            "fileid": 1,
            "filetypename": "Configuration",
            "s3URL": "https://transformation-files.s3.amazonaws.com/Gulf/202511020/1_Configuration.xls?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220203T145511Z&X-Amz-SignedHeaders=host&X-Amz-Expires=86400&X-Amz-Credential=AKIAVF5MBF6ACCHMQW4S%2F20220203%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=9ee505ee25b247ae428b6f84303156f6b55413d13e3d10a24a6d2bf8e127f32f",
            "clientfilename": "xyz.xls",
            "fileContentType": null,
            "moduleid": "101",
            "uploadstatus": "200",
            "uploadstatusremark": "1"
        },
        {
            "fileid": 2,
            "filetypename": "Programming Grid",
            "s3URL": "https://transformation-files.s3.amazonaws.com/Gulf/202511020/2_Programming%20Grid.xls?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220203T145511Z&X-Amz-SignedHeaders=host&X-Amz-Expires=86399&X-Amz-Credential=AKIAVF5MBF6ACCHMQW4S%2F20220203%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=0ebc45705b6f805e0ef1573371ea361b9df7fb74e12b5df8c2f773c44b9adb69",
            "clientfilename": "abc.xls",
            "fileContentType": null,
            "moduleid": "101",
            "uploadstatus": "200",
            "uploadstatusremark": "1"
        }
    ]
}