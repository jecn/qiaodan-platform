(function ($) {

    var hash = window.location.hash, start = new Date();
    $(function () {
        window.onbeforeunload = function () { return "您确定要退出本程序？"; };

        /*window.mainpage.initTimer();
        window.mainpage.bindButtonEvent();
        window.mainpage.bindTabsButtonEvent();*/

        var layout = $("#mainLayout");
        $.util.exec(function () {
            layout.removeClass("hidden").layout("resize");

            $("#maskContainer").remove();

            //  判断当浏览器窗口宽度小于像素 1280 时，右侧 region-panel 自动收缩
            var size = $.util.windowSize();
            if (size.width < 1280) { 
            	layout.layout("collapse", "east"); 
            }

            var stop = new Date();
            $.easyui.messager.show({ msg: "当前页面加载耗时(毫秒)：" + $.date.diff(start, "ms", stop), position: "bottomRight" });
        });
    });
})(jQuery);