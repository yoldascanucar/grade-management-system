<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
    let inactivityTime = function () {
        let time;
        let logoutTime = 300000s;

        window.onload = resetTimer;
        document.onmousemove = resetTimer;
        document.onkeypress = resetTimer;

        function logout() {
            window.location.href = "<%=request.getContextPath()%>/logout";
        }

        function resetTimer() {
            clearTimeout(time);
            time = setTimeout(logout, logoutTime);
        }
    };

    inactivityTime();
</script>
