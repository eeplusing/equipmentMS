/**
* Image Magnifier Plugin
*   @version 1.0.0
*   @author zjcqoo@gmail.com
*/
(function () {
    var _VER_ = navigator.userAgent;
    var _FF_ = /Firefox/.test(_VER_);
    var _IE_ = /MSIE/.test(_VER_);
    var _IE6_ = /MSIE 6/.test(_VER_);
    var _IE7_ = /MSIE 7/.test(_VER_) || document.documentMode == 7;

    var WHELL_EVENT = _FF_ ? "DOMMouseScroll" : "mousewheel";
    var STD = !!window.addEventListener;



    var _TEMP_ = document.createElement("div");

    function $create(html) {
        _TEMP_.innerHTML = html;
        return _TEMP_.firstChild;
    }


    var $bind = STD ?
		function (dom, name, fn) { dom.addEventListener(name, fn, false) } :
		function (dom, name, fn) { dom.attachEvent("on" + name, fn) }

    var $unbind = STD ?
		function (dom, name, fn) { dom.removeEventListener(name, fn, false) } :
		function (dom, name, fn) { dom.detachEvent("on" + name, fn) }


    var de = document.documentElement;
    var body = document.body;

    var GLASS_W = 300;
    var GLASS_H = 300;
    var GLASS_R = 74;
    var GLASS_D = GLASS_R * 2;
    var GLASS_X = 40;
    var GLASS_Y = 11;
    var GLASS_CX = GLASS_X + GLASS_R;
    var GLASS_CY = GLASS_Y + GLASS_R;

    var ZOOM_STEP = 0.2;
    var iZoomMin = 1.5;
    var iZoomMax = 3.5;
    var iZoom = 2.5;


    var LOAD_PEND = 1;
    var LOAD_DONE = 2;

    var RND = Math.round;


    var css3;
    var oBox, styBox;
    var oBG, styBG;
    var oCircle;

    var oImg;
    var oLoader;
    var mapImgWidth = {};

    var iOffX = 0, iOffY = 0;
    var bShow;




    function setZoomPic(url) {
        if (css3)
            oCircle.style.backgroundImage = "url(" + url + ")";
        else
            oBG.src = url;
    }


    function handleLoaderComplete() {
        mapImgWidth[this.big] = this.width;
        iZoomMax = this.width / oImg.width;

        //
        // 加载的不是当前的大图。
        // (上一张没加载完就移出区域，
        // 则放弃替换)
        //
        if (this.small != oImg.src)
            return;

        setZoomPic(this.src);
        updateZoom();
    }


    function handleMouseOver(e) {
        e = e || event;

        var img = e.target || e.srcElement;
        var src_big = img.getAttribute("bigsrc");

        if (!src_big)
            return;

        oImg = img;

        var value = mapImgWidth[src_big];

        if (value > 0) {
            iZoomMax = value / img.width;
            setZoomPic(src_big);
        }
        else {
            setZoomPic(img.src);

            if (value == null) {
                //
                // 暂时先用小图片放大，
                // 等大图下载完成再换上
                //
                mapImgWidth[src_big] = 0;

                var loader = new Image;

                loader.onload = handleLoaderComplete;
                loader.big = src_big;
                loader.small = img.src;
                loader.src = src_big;
            }
        }

        $bind(document, "mousemove", handleMouseMove);
        $bind(document, WHELL_EVENT, handleMouseWheel);
    }



    function handleMouseMove(e) {
        e = e || event;

        var dx = de.scrollLeft || body.scrollLeft;
        var dy = de.scrollTop || body.scrollTop;

        var x = e.pageX || (e.clientX + dx);
        var y = e.pageY || (e.clientY + dy);


        var rect = oImg.getBoundingClientRect();

        var left = rect.left + dx;
        var right = rect.right+dx;
        var top = rect.top + dy;
        var bottom = rect.bottom + dy;


        //alert(r.left+":"+r.top+ "\n" + x+","+y+"\n"+left + "," + top + " : " + right + "," + bottom)

        //
        // 鼠标移出原图片
        //
        if (x < left || x > right ||
			y < top || y > bottom) {
            $unbind(document, "mousemove", handleMouseMove);
            $unbind(document, WHELL_EVENT, handleMouseWheel);

            styBox.display = "none";
            bShow = false;
            return;
        }


        if (!bShow) {
            bShow = true;
            styBox.display = "block";
            updateZoom();
        }

        //
        // 更新放大镜位置
        //
        styBox.left = x - GLASS_CX + "px";
        styBox.top = y - GLASS_CY + "px";

        iOffX = x - left;
        iOffY = y - top;

        // ie6,7 图片不会动，强制设置下尺寸
        if (_IE6_ || _IE7_)
            updateZoom();

        updatePos();
    }


    function handleMouseWheel(e) {
        e = e || event;

        var whell = e.wheelDelta || -e.detail;

        iZoom += (whell > 0 ? ZOOM_STEP : -ZOOM_STEP);

        updateZoom();
        updatePos();

        // 防止滚屏
        if (STD) {
            e.stopPropagation();
            e.preventDefault();
        }
        else {
            e.returnValue = false;
        }
    }


    function updatePos() {
        var left = RND(-iZoom * iOffX + GLASS_R);
        var top = RND(-iZoom * iOffY + GLASS_R);

        if (css3) {
            oCircle.style.backgroundPosition = left + "px " + top + "px";
        }
        else {
            styBG.pixelLeft = left;
            styBG.pixelTop = top;
        }
    }


    function updateZoom() {
        if (iZoom < iZoomMin)
            iZoom = iZoomMin;
        else if (iZoom > iZoomMax)
            iZoom = iZoomMax;

        var width = RND(oImg.width * iZoom);
        var height = RND(oImg.height * iZoom);

        if (css3) {
            oCircle.style.backgroundSize = width + "px " + height + "px";
        }
        else {
            styBG.pixelWidth = width;
            styBG.pixelHeight = height;
        }
    }


    function init() {
        var css_box =
			"width:" + GLASS_W + "px;" +
			"height:" + GLASS_H + "px;" +
			"position: absolute;";

        var css_back =
			"left:" + GLASS_X + "px;" +
			"top:" + GLASS_Y + "px;" +
			"width:" + GLASS_D + "px;" +
			"height:" + GLASS_D + "px;" +
			"background-color: #FFF;" +
			"position: absolute;";


        oBox = $create("<div style='" + css_box + "; z-index:999; cursor:none; display:none'></div>");
        styBox = oBox.style;

        css3 = "borderRadius" in styBox;

        //
        // css3 =>
        //   oBox: 顶层容器
        //     oBG: 圆形的DIV，可缩放的背景图片
        //     front: 放大镜图片
        //
        // ie =>
        //   oBox: 顶层容器
        //     oCircle: 蒙板容器
        //        oBG: 可缩放的IMG图片
        //        mask: 蒙板图片
        //     front: 放大镜图片
        //
        var front;

        if (css3) {
            oCircle = $create("<div style='" + css_back + "border-radius:" + GLASS_R + "px; background-repeat:no-repeat'></div>");
            front = $create("<img src='/InputMS/images/pic.png' style='position: absolute'>");
        }
        else {
            // 缩放的img元素
            oBG = $create("<img style='position:absolute'>");
            styBG = oBG.style;

            // 此容器应用蒙板滤镜
            oCircle = $create("<div style='" + css_back + "filter:chroma(color=#123456); background-repeat:no-repeat; overflow:hidden'></div>");

            var mask = $create("<img src='/InputMS/images/mask.png' style='position:absolute'>");

            oCircle.appendChild(oBG);
            oCircle.appendChild(mask);

            // 支持ie6加载png-24
            front = $create("<div style='background:url(/InputMS/images/pic.png); _background:none; _filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=/InputMS/images/pic.png);" + css_box + "'></div>");

            styBox.cursor = "url(/InputMS/images/blank.ico)";
        }

        oBox.appendChild(oCircle);
        oBox.appendChild(front);
        document.body.appendChild(oBox);

        $bind(document, "mouseover", handleMouseOver);

        styBox.display = "none";
    }

    init();
})();