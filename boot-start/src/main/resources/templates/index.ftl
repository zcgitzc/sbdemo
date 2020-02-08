<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>Hello world</title>

    <script type="text/javascript" src="https://cdn.staticfile.org/jquery/3.4.1/jquery.js"></script>
    <script type="text/javascript" src="https://cdn.staticfile.org/webuploader/0.1.1/webuploader.js"></script>

    <link rel="stylesheet" type="text/css" href="https://cdn.staticfile.org/webuploader/0.1.1/webuploader.css">
    <link rel="stylesheet" type="text/css" href="http://fex.baidu.com/webuploader/css/bootstrap.min.css">

</head>
<body>
<p id="root">
    Hello world 123
</p>

<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>
</div>

</body>


<script>
    this.window.appConfig = {
        map: ${map},
        list: ${list},
        hello: "${hello}"
    }

    var $ = jQuery,
        $list = $('#thelist'),
        $btn = $('#ctlBtn'),
        state = 'pending',
        uploader;

    //一个GUID
    var GUID = WebUploader.Base.guid();
    var uploader = WebUploader.create({
        // swf文件路径
        swf: 'https://cdn.staticfile.org/webuploader/0.1.1/Uploader.swf',
        // 文件接收服务端。
        server: '/upload/chunkUpload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        chunked: true,//开始分片上传
        chunkSize: 1024 * 1024,//每一片的大小
        formData: {
            guid: GUID //自定义参数，待会儿解释
        }
    });

    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function (file) {
        $list.append('<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
            '</div>');
    });


    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css('width', percentage * 100 + '%');
    });


    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css('width', percentage * 100 + '%');
    });

    uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).find('p.state').text('已上传');
        $.post('/upload/chunkMerge', {guid: GUID, fileName: file.name}, function (data) {
            $list.text('已上传');
        });
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });

    $('#pause').click(function () {
        uploader.stop(true);
        $('#ctlBtn').removeAttr('disabled');
        $("#ctlBtn").text("继续上传");
        $("#uploader .state").html("暂停中...");
        $("#uploader .progress-bar").removeClass('progress-bar-striped').removeClass('active');
    });

    uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });


    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
</script>
</html>