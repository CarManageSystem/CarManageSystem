<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="基于HTML5的多图Ajax上传 » 张鑫旭-鑫空间-鑫生活" />
<meta name="description" content="张鑫旭web前端学习实例页面 基于HTML5的多图Ajax上传" />
<meta name="keywords" content="web前端, HTML5, ajax, javascript, 图片上传" />
<meta name="author" content="张鑫旭, zhangxixnu" />
<title>基于HTML5的多图Ajax上传 » 张鑫旭-鑫空间-鑫生活</title>

<style>
.upload_box{width:800px; margin:1em auto;}
.upload_main{border-width:1px 1px 2px; border-style:solid; border-color:#ccc #ccc #ddd; background-color:#fbfbfb;}
.upload_choose{padding:1em;}
.upload_drag_area{display:inline-block; width:60%; padding:4em 0; margin-left:.5em; border:1px dashed #ddd; background:#fff url(http://rescdn.qqmail.com/zh_CN/htmledition/images/func/dragfile07bf38.gif) no-repeat 20px center; color:#999; text-align:center; vertical-align:middle;}
.upload_drag_hover{border-color:#069; box-shadow:inset 2px 2px 4px rgba(0, 0, 0, .5); color:#333;}
.upload_preview{border-top:1px solid #bbb; border-bottom:1px solid #bbb; background-color:#fff; overflow:hidden; _zoom:1;}
.upload_append_list{height:100px; padding:0 1em; float:left; position:relative;font-size:0.5em}
.upload_delete{margin-left:2em;}
.upload_image{max-height:50px; padding:5px;}
.upload_submit{padding-top:1em; padding-left:1em;}
.upload_submit_btn{display:none; height:32px; font-size:14px;}
.upload_loading{height:250px; background:url(http://www.zhangxinxu.com/study/image/loading.gif) no-repeat center;}
.upload_progress{display:none; padding:5px; border-radius:10px; color:#fff; background-color:rgba(0,0,0,.6); position:absolute; left:25px; top:45px;}
</style>
</head>

<body>
<div id="main">
	<h1>基于HTML5的多图Ajax上传实例页面</h1>
    <div id="body" class="light">
    	<div id="content" class="show">
            <div class="demo">
            	<form id="uploadForm" action="/CarManageSystem/upload_files.action" method="post" enctype="multipart/form-data">
                    <div class="upload_box">
                        <div class="upload_main">
                            <div class="upload_choose">
                                <input class="form-control" name="cardnum" type="hidden" value="20160531">
                                <input class="form-control" name="type1" type="hidden" value="4"> 
                                <input class="form-control" name="type2" type="hidden" value="2">
                                <input id="fileImage" type="file" size="30" name="file1" multiple />
                                
                            </div>
                            <div id="preview" class="upload_preview"></div>
                        </div>
                        <button type="submit" style="float:left;margin-left:15px;width:50px;height:22px;line-height:15px;">上传</button>
                        
                        <div class="upload_submit">
                            <button type="button" id="fileSubmit" class="upload_submit_btn">确认上传图片</button>
                        </div>
                        <!-- 
                        
                         -->
                        <div id="uploadInf" class="upload_inf"></div>
                    </div>
                    
                </form>
                
            </div>
        </div>       
    </div>
</div>
<script src="http://libs.baidu.com/jquery/1.4.4/jquery.js"></script>
<script src="/CarManageSystem/js/zxxFile.js"></script>
<script src="/CarManageSystem/js/jquery.form.js"></script> 
<script type="text/javascript">
//绑定表单提交事件处理器 ,jquery.form.js
$("#uploadForm").submit(function(){
  var options = {
          success: function (data) {
          	alert("sus");
          }
      };
 $(this).ajaxSubmit(options); 
// 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false 
	return false; 
});

var params = {
	fileInput: $("#fileImage").get(0),
	//dragDrop: $("#fileDragArea").get(0),
	upButton: $("#fileSubmit").get(0),
	url: $("#uploadForm").attr("action"),
	filter: function(files) {
		var arrFiles = [];
		for (var i = 0, file; file = files[i]; i++) {
			if (file.type.indexOf("image") == 0) {
				if (file.size >= 512000) {
					alert('您这张"'+ file.name +'"图片大小过大，应小于500k');	
				} else {
					arrFiles.push(file);	
				}			
			} else {
				alert('文件"' + file.name + '"不是图片。');	
			}
		}
		return arrFiles;
	},
	onSelect: function(files) {
		var html = '', i = 0;
		$("#preview").html('<div class="upload_loading"></div>');
		var funAppendImage = function() {
			file = files[i];
			if (file) {
				var reader = new FileReader()
				reader.onload = function(e) {
					html = html + '<div id="uploadList_'+ i +'" class="upload_append_list"><p><strong>' + file.name + '</strong>'+ 
						'<a href="javascript:" class="upload_delete" title="删除" data-index="'+ i +'">删除</a><br />' +
						'<img id="uploadImage_' + i + '" src="' + e.target.result + '" class="upload_image" /></p>'+ 
						'<span id="uploadProgress_' + i + '" class="upload_progress"></span>' +
					'</div>';
					
					i++;
					funAppendImage();
				}
				reader.readAsDataURL(file);
			} else {
				$("#preview").html(html);
				if (html) {
					//删除方法
					$(".upload_delete").click(function() {
						ZXXFILE.funDeleteFile(files[parseInt($(this).attr("data-index"))]);
						return false;	
					});
					//提交按钮显示
					$("#fileSubmit").show();	
				} else {
					//提交按钮隐藏
					$("#fileSubmit").hide();	
				}
			}
		};
		funAppendImage();		
	},
	onDelete: function(file) {
		$("#uploadList_" + file.index).fadeOut();
	},
	//onProgress: function(file, loaded, total) {
		//var eleProgress = $("#uploadProgress_" + file.index), percent = (loaded / total * 100).toFixed(2) + '%';
		//eleProgress.show().html(percent);
	//},
	onSuccess: function(file, response) {
		$("#uploadInf").append("<p>上传成功，图片地址是：" + response + "</p>");
	},
	onFailure: function(file) {
		$("#uploadInf").append("<p>图片" + file.name + "上传失败！</p>");	
		$("#uploadImage_" + file.index).css("opacity", 0.2);
	},
	onComplete: function() {
		//提交按钮隐藏
		$("#fileSubmit").hide();
		//file控件value置空
		$("#fileImage").val("");
		$("#uploadInf").append("<p>当前图片全部上传完毕，可继续添加上传。</p>");
	}
};
ZXXFILE = $.extend(ZXXFILE, params);
ZXXFILE.init();
</script>


</body>
</html>
