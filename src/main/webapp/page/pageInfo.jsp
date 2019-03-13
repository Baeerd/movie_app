<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<ul class="pagination m-t-30">
    <c:choose>
        <c:when test="${page.pages == 1}">
            <li><a href="javascript:firstPage(${page.pageNum});" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
            <li class="active"><a href="javascript:void(0);">1</a></li>
            <li><a href="javascript:endPage(${page.pageNum});" aria-label="Next"><span aria-hidden="true">»</span></a></li>
        </c:when>
        <c:otherwise>
            <li><a href="javascript:firstPage(${page.pageNum});" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
            <c:forEach begin="1" end="${page.pages}" var="p">
                <c:choose>
                    <c:when test="${p == page.pageNum}">
                        <li class="active"><a href="javascript:void(0);">${p}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="javascript:showPage(${p});">${p}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <li><a href="javascript:endPage(${page.pageNum});" aria-label="Next"><span aria-hidden="true">»</span></a></li>
        </c:otherwise>
    </c:choose>
</ul>
<script type="text/javascript">
    // 首页
    function firstPage(curtPage) {
        if(curtPage == 1) {
            return;
        }
        reQueryForm(1);
    }

    // 尾页
    function endPage(curtPage) {
        if(curtPage == ${page.pages}) {
            return;
        }
        reQueryForm(${page.pages});
    }

    // 第几页
    function showPage(pageNum) {
        reQueryForm(pageNum);

    }

    function reQueryForm(pageNum) {
        $("form>input:eq(0)").val(pageNum);
        $("form>input:eq(1)").val("10");
        $("form:eq(0)").submit();
    }
</script>