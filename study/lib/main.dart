import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:study/lcontainer.dart';
import 'package:study/login.dart';
import 'package:http/http.dart' as http;
import 'dart:convert' as convert;

import 'package:study/my_inherited.dart';
import 'package:study/theme.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.

  @override
  Widget build(BuildContext context) {

    print("main start");

    Future(() => print("task1"));

    final future = Future(() => null);
    Future(() => print("task2")).then((_) {
      print("task3");
      // scheduleMicrotask(() => print('task4'));
    }).then((_) => print("task5"));


    future.then((_) => print("task6"));
    // scheduleMicrotask(() => print('task7'));

    Future(() => print('task8'))
        .then((_) => Future(() => print('task9')))
        .then((_) => print('task10'));

    Future(() => print('task11'));

    print("main end");


    print("this is home");

    return ChangeNotifierProvider.value(
      value: CounnterModel(),
      child: MainWidget()
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  static const methodChannel = MethodChannel("orange");
  // Future<SharedPreferences> _pref = SharedPreferences.getInstance();

  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
    });

    Navigator.of(context).pushNamed("login");
  }

  Future<void> handleChannel() async{
    var result = await methodChannel.invokeMethod("lanhai");
    print("Result $result");
  }
  
  Future<dynamic> handleFuttler(MethodCall call) async{
    if (call.method == 'flu') {
      print('call flutter method');
      print("arguments ${call.arguments}");
      return "flutter";
    }
  }

  // Future<Void> add() async{
  //   SharedPreferences preferences = await _pref;
  //   preferences.getString("key");
  //   preferences.setString("key", "value");
  //   preferences.commit();
  // }

  getHttpInfo() async{
    var url = Uri.https("www.baidu.com", "/lp");
    var response = await http.get(url);
    if (response.statusCode == 200) {
      convert.jsonDecode(response.body) as Map<String, dynamic>;
    }
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    methodChannel.setMethodCallHandler((call) => handleFuttler(call));
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun evermethod above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
            FlatButton(onPressed: (){
              print('11111111111111');
              handleChannel();
            }, child: Text("method channel"))
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}

class MainWidget extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or press Run > Flutter Hot Reload in a Flutter IDE). Notice that the
        // counter didn't reset back to zero; the application is not restarted.
        primarySwatch: Provider.of<CounnterModel>(context).themeColor,
      ),
      routes: {
        "home": (context) => MyApp(),
        "login": (context) => LoginWidget(),
        "lc": (context) => LContainer(),
        "inherited": (context) => UserCenterWidget(),
        "theme": (context) => ThemeWidget(),
      },
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }

}