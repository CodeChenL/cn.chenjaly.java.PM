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
    </style>
    <title></title>
    <script src="${ctx }/js/Ueditor/wangEditor.min.js"></script>
    <script src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>

    <script type="text/javascript">

        $(function () {
            /** 部门表单提交 */
            $("#noticeForm").submit(function () {
                var title = $("#title");
                var msg = "";
                if ($.trim(title.val()) == "") {
                    msg = "标题不能为空！";
                    title.focus();
                }
                if (msg != "") {
                    $.ligerDialog.error(msg);
                    return false;
                } else {
                    return true;
                }
                $("#noticeForm").submit();
            });
        });


    </script>
</head>
<body>
<br>
<form id="noticeForm" method="post" action="${ctx}/noticeaddsave.action">
    <div>
        <div>
            <span>标题：</span>
            <input type="text" name="title" id="title" size="24"/>
        </div>
        <br>
        <div>
            <span>类别：</span>
            <select name="type_id" style="width:200px;">
                <option value="0">--请选择类别--</option>
                <c:forEach items="${requestScope.typeList }" var="type">
                    <option value="${type.id }">${type.name }</option>
                </c:forEach>
            </select>
        </div>
        <br>
        <%--文本编辑框--%>
        <div id="intro" class="TextContent,w-e-text-container"></div>
        <textarea style="display: none" name="mytxtIntro" id="txtIntro"></textarea>
        <%--提交按钮--%>
        <input class="btn btn-primary" type="submit" value="确认提交">
    </div>
</form>

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

</script>

</html>