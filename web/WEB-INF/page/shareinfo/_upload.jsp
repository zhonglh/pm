<%@page contentType="text/html;charset=UTF-8"%>
<div>
	<br><br><br>
	<center>
  	<div id="uploadFileButton" class="btn btn-primary fileinput-button">  	
    	<span>上传文件</span>
    	<input type="file" id="file" name="file" class="fileupload" data-no-uniform="true" data-url="FileInfoAction.do?method=saveFile" data-form-data='{"parent_id":"${fileInfo1.parent_id}"}'>
  	</div>
	</center>
</div>

<br>
 


<div id="uploadFileProgress" class="modal fade" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">上传文件</h4>
      </div>
      <div class="modal-body">
        <div class="progress">
		  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
			<span class="sr-only">0%</span>
		  </div>
		</div>
      </div>
      <div class="modal-footer">
        <button id="uploadFileCancelButton" type="button" class="btn btn-default" data-dismiss="modal" onclick="location.reload()">取消</button>
        <button id="uploadFileConfirmButton" type="button" class="btn btn-primary" onclick="closeDialog()">确认</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>

<link rel="stylesheet" href="${ctx}/s/jquery-file-upload/css/jquery.fileupload.css">
<script src="${ctx}/s/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
<script src="${ctx}/s/jquery-file-upload/js/jquery.iframe-transport.js"></script>
<script src="${ctx}/s/jquery-file-upload/js/jquery.fileupload.js"></script>

<script type="text/javascript">

function closeDialog(){
	var json = 		 
	{
		"statusCode":"200", 
		"message":"文件上传成功", 
		"navTabId":"", 
		"rel":"rel71",
		"callbackType":"closeCurrent",
		"forwardUrl":"",
		"rownum":"",
		"other":""
	};
	parent.dialogAjaxDone(json);
	
}
function generateFileupload(maxLimitedSize) {
    $('.fileupload').fileupload({
        dataType: 'json',
        add: function (e, data) {
			var file = data.files[0];
			if (file.size > maxLimitedSize) {
				parent.alertMsg.error("文件过大，上传文件不能超过1G");
			} else {
				data.submit();
			}
        },
		submit: function (e, data) {
			var $this = $(this);
			data.jqXHR = $this.fileupload('send', data);
			$('.progress-bar').css(
                'width',
                '0%'
            ).html('0%');
			$('#uploadFileConfirmButton').hide();
			$('#uploadFileProgress').modal('show');
			return false;
		},
        done: function (e, data) {
        	debugger;
        	if(data.result && data.result.msg){
        		if(data.result.msg.indexOf("Index_1")!=-1){
        			parent.alertMsg.error("上传失败,文件已经存在！");  
        			return false;
        		}else {
        			parent.alertMsg.error("上传失败！");  
        			return false;
        		}
        	}else if(data.result.indexOf("false")!=-1){ 
        		parent.alertMsg.error("上传失败！");
    			return false;
        	}else if(data.result.indexOf("true")!=-1){ 
            	closeDialog();
        	}else {
        		parent.alertMsg.error(data.result);
        	}
        },
		fail: function (e, data) {
			parent.alertMsg.error("上传失败");
		},
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('.progress-bar').css(
                'width',
                progress + '%'
            ).html(progress + '%');
        }
    });
}

$(function () {
	generateFileupload(1024 * 1024 * 1024);
	$("#file").click();
	$("#uploadFileButton").click();
});
</script>



