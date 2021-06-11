import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

// ignore: must_be_immutable
class MyInheritedWidget extends InheritedWidget {

  MyInheritedWidget({@required this.data, Widget child}) : super(child: child);

  int data;

  static MyInheritedWidget of(BuildContext context) {
    return context.dependOnInheritedWidgetOfExactType<MyInheritedWidget>();
  }

  @override
  bool updateShouldNotify(covariant MyInheritedWidget oldWidget) {
    // TODO: implement updateShouldNotify
    return this.data == oldWidget.data;
  }
}

class UserCenterWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return UserCenterState();
  }
}

class UserCenterState extends State<UserCenterWidget> {

  var content = 1;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('UserCenterWidget'),),
      body: MyInheritedWidget(
        data: content,
        child: Column(
          children: [
            MyTextWidget(),
            FloatingActionButton(
                child: Text('second'),
                onPressed: (){
                  setState(() {
                    content += 1;
                  });
                }
            ),
            Image.asset("name")
          ],
        ),
      )
    );
  }
}

class MyTextWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyTextState();
  }

}

class MyTextState extends State<MyTextWidget> {
  @override
  Widget build(BuildContext context) {
    return Text(MyInheritedWidget.of(context).data.toString());
  }

}