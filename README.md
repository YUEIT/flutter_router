# flutter 路由解决方案 
    
   结合 flutterboots 实现native与flutter间路由跳转；flutter层通过注解生成路由表，并根据路由表中地址和参数完成页面跳转

     路由生成命令
            flutter packages pub run build_runner build --delete-conflicting-outputs
     建议先执行
            flutter packages pub run build_runner clean