
$(document).ready(function(){  // 在一开始绑定a的点击切换轮播图事件
    $(".banner>ul>li>a").click(function(){
        banner(this);
        return false;
    });
    bannerRoll();
});
// 用a标签按钮切换轮播图
function banner(obj) {  // obj 当前标签
    var img = $(obj).parent().parent().prev().children();
    $(img).attr("src", $(obj).attr("href"));
}
var t;  // 用于标识定时器
function bannerRoll() {
    t = setInterval(showPic, 3000);     // 设置每三秒调用换图函数换张图
}
var x = 0;  // 全局变量，记录当前第几张轮播图
function showPic() {
    // 获取a父标签li的个数，轮流播放
    var arr_uls = $(".banner>ul");
    arr_uls.each(function() {   // 遍历ul标签，给它的img换张图
        if (x >= $(this).children().length)
            x = 0;
        $(this).prev("a").attr("href",function(){
            if(x==0){
                //$(this).prev("a").children("image").attr("src", "images/cat_s.gif");
                return "viewCategory?categoryId=CATS";
            }

            else if (x==1) {
                return "viewCategory?categoryId=DOGS";
            }
            else if (x==2)
                return "viewCategory?categoryId=FISH";
            else if (x==3)
                return "viewCategory?categoryId=BIRDS";
        })
        $(this).prev("a").children("img").attr("src", arr_uls.children("li").eq(x).children("a").attr("href"));
    });
    x++;
}
//鼠标经过盒子时，清除定时
$(".banner").mouseenter( function(){
    clearInterval(t);
}).mouseleave( function(){
    //鼠标离开盒子时，再次定时
    bannerRoll();
});





// $(this).prev("a").attr("href",function(){
//     if(x=1) return "viewCategory?categoryId=CATS";
//     else if (x=2) return "viewCategory?categoryId=DOGS";
//     else if (x=3) return "viewCategory?categoryId=FISH";
//     else if (x=4) return "viewCategory?categoryId=BIRDS";
// })