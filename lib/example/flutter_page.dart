
import 'package:flutter_route/router/route.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_boost/flutter_boost.dart';

import 'router_path.dart';


@ARoute(url: RouterPath.SECOND_PAGE)
class FlutterPage extends StatelessWidget {
  ARouteOption option;
  FlutterPage(this.option);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Page 2"),
      ),
      body: Center(
        child: Column(
          children: <Widget>[

            RaisedButton(
              onPressed: () {

                //导航到新路由
                FlutterBoost.singleton
                    .open("native://app/nativePage", urlParams: <dynamic,dynamic>{
                  "query": {"aaa": "bbb"}
                });
              },
              child: Text("Home Page"),
            ),
            Text("url  "+ option.urlpattern,
              textAlign: TextAlign.left,
            ),
            Text("params" + option.params.toString(),
              textAlign: TextAlign.left,
            ),
          ],
        ),
      ),
    );
  }
}
