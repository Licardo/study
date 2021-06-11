import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

// class LoginWidget extends StatefulWidget {
//   @override
//   State<StatefulWidget> createState() {
//     return LoginState();
//   }
//
// }

class LoginWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var model = Provider.of<CounnterModel>(context);
    return Scaffold(
      appBar: AppBar(title: Text("login"),),
      body: Column(
        children: [
          Text("${model.counter}"),
          FloatingActionButton(
              child: Text("${model.counter}"),
              onPressed: () => model.add() ),
          FloatingActionButton(
              child: Text('jump'),
              onPressed: () => Navigator.pushNamed(context, 'inherited'))
        ],
      ),
    );
  }

}

class CounnterModel with ChangeNotifier {
  int _count = 0;
  get counter => _count;
  void add() {
    _count++;
    notifyListeners();
  }
}