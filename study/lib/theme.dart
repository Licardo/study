import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:provider/provider.dart';

import 'login.dart';

class ThemeWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ThemeState();
  }

}

class ThemeState extends State<ThemeWidget> {

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    var model = Provider.of<CounnterModel>(context);
    return Theme(
        data: ThemeData(
          primarySwatch: model.themeColor,
          iconTheme: IconThemeData(color: model.themeColor)
        ),
        child: Scaffold(
          appBar: AppBar(title: Text('主题'),),
          body: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(Icons.favorite),
                  Icon(Icons.airport_shuttle),
                  Text('跟随主题变化')
                ],
              ),
              Theme(
                  data: ThemeData(
                    iconTheme: IconThemeData(color: Colors.black)
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Icon(Icons.favorite),
                      Icon(Icons.airport_shuttle),
                      Text('固定黑色')
                    ],
                  ))
            ],
          ),
          floatingActionButton: FloatingActionButton(
            child: Text('switch'),
            onPressed: () {
              setState(() {
                Color themeColor = model.themeColor == Colors.teal ? Colors.blue : Colors.teal;
                model.switchTheme(themeColor);
              });
            },
          ),
        )
    );
  }

}