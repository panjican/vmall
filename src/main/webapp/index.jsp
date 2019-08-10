<html>
<%@ page contentType="text/html; charset=UTF-8"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<body>
<h2>Hello World!</h2>
springmvc上传文件
<form name="uploadfile" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="上传" />
</form>
富文本图片上传文件
<form name="uploadrichfile" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="上传" />
</form>
</body>
</html>
