<!DOCTYPE html>
<html>
<head>
    <title>Upload Files using XMLHttpRequest - Minimal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form id="form1" enctype="multipart/form-data" method="post" action="/upload">
        <div class="row">
            <input type="text" name="name" , id="name">
            <label for="fileToUpload">Select a File to Upload</label><br/>
            <input type="file" name="fileToUpload" id="fileToUpload" onchange="fileSelected()"/>
        </div>
        <div id="fileName"></div>
        <div id="fileSize"></div>
        <div id="fileType"></div>
        <div class="row">
            <input type="button" onclick="uploadFile()" value="Upload"/>
        </div>
        <div class="progress">
            <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="40"
                 aria-valuemin="0" aria-valuemax="100" style="width:0%" id="progressNumber">
            </div>
        </div>
    </form>
</div>
<script src="/bower_components/jquery/dist/jquery.min.js"></script>
<script type="application/javascript">
    function fileSelected() {
        var file = document.getElementById('fileToUpload').files[0];
        if (file) {
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

            document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
            document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
            document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
        }
    }

    function uploadFile() {
        var fd = new FormData();
        fd.append("name", $('#name').val());
        fd.append("file", document.getElementById('fileToUpload').files[0]);
        var xhr = new XMLHttpRequest();
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open("POST", "/upload");
        xhr.send(fd);
    }

    function uploadProgress(evt) {
        if (evt.lengthComputable) {
            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
            $('#progressNumber').css('width', percentComplete.toString() + '%');
//            $('#progressNumber').val(percentComplete.toString() + '%');
            document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
        }
    }

    function uploadComplete(evt) {
        /* This event is raised when the server send back a response */
    }

    function uploadFailed(evt) {
        alert("There was an error attempting to upload the file.");
    }

    function uploadCanceled(evt) {
        alert("The upload has been canceled by the user or the browser dropped the connection.");
    }
</script>
</body>
</html>