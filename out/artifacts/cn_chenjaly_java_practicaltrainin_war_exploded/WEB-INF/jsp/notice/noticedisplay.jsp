<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        .w-e-text-container {
            height: 650px !important; /*!important是重点，因为原div是行内样式设置的高度300px*/
        }

        .text {
            text-align: center
        }
    </style>
    <title></title>
    <script src="${ctx }/js/Ueditor/wangEditor.min.js"></script>
    <script src="${ctx }/js/jquery-1.11.0.js"></script>
</head>
<body>
<div class="text"><h1> ${notice.title }</h2></div>
<br>
<div>
    <div>
        <%--文本编辑框--%>
        <div id="intro" class="TextContent,w-e-text-container">
            ${notice.remark }
        </div>
        <textarea style="display: none" name="mytxtIntro" id="txtIntro"></textarea>
    </div>


</body>


<%--文本编辑器插件js--%>
<script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#intro');

    //    获取隐藏控件<textarea>的id，用于显示内容，也方便后台获取内容
    var $text1 = $('#txtIntro');

    // 监控wangEditor中的内容变化，并将html内容同步更新到 textarea
    editor.customConfig.onchange = function (html) {
        $text1.val(html);
    }

    //    设置上传本地图片到服务器
    editor.customConfig.uploadImgShowBase64 = true;
    editor.create();
    $text1.val(editor.txt.html());// 初始化 textarea 的值
    editor.$textElem.attr('contenteditable', false)
</script>

</html>