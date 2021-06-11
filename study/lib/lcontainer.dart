import 'package:flutter/material.dart';
class LContainer extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return LContainerState();
  }

}

class LContainerState extends State<LContainer> {
  @override
  Widget build(BuildContext context) {
    return Navigator(
      pages: List<Page<dynamic>>.of([LPage<dynamic>(
        pageName: 'lp',
        arguments: null
      )]),
      onPopPage: (Route<dynamic> route, dynamic result) {
        return true;
      },
    );
  }

}

class LPage<T> extends Page<T> {
  var pageName;
  var arguments;

  LPage({LocalKey key, this.pageName, this.arguments})
      : super(key: key, name: pageName, arguments: arguments);

  @override
  Route<T> createRoute(BuildContext context) {
    return route;
  }

  Route<T> _route;

  Route<T> get route => _route;
}
