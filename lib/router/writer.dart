
import 'collector.dart';
import 'page_config_map_util.dart';
import 'tpl.dart';

class Writer {
  Collector collector;
  Writer(this.collector);

  String instanceCreated() {
    return instanceCreatedTpl;
  }

  String instanceFromClazz() {
    final StringBuffer buffer = new StringBuffer();
    buffer..writeln('switch(clazz) {');
    final Map<String, bool> mappedClazz = <String, bool>{};
    final Function writeClazzCase = (Map<String, dynamic> config) {
      final dynamic clazz = config[wK('clazz')];
      if (mappedClazz[clazz] == null) {
        mappedClazz[clazz] = true;
      } else {
        return;
      }
      buffer.writeln('case ${clazz}: return new ${clazz}(option);');
    };
    collector.routerMap
        .forEach((String url, List<Map<String, dynamic>> configList) {
      configList.forEach(writeClazzCase);
    });
    buffer..writeln('default:return null;')..writeln('}');
    return buffer.toString();
  }

  String write() {
    final List<Map<String, String>> refs = <Map<String, String>>[];
    final Function addRef = (String path) {
      refs.add(<String, String>{'path': path});
    };
    collector.importList.forEach(addRef);
    String import = "";
    collector.importList.forEach((String path){
      import = import + "import '" + path + "';" + "\n";
    });
    String mapString = "static final Map<String, List<Map<String, dynamic>>> innerRouterMap = <String, List<Map<String, dynamic>>>";
    String mapCode = mapString + collector.routerMap.toString() + ";";
    String funString = "static dynamic instanceFromClazz(Type clazz, dynamic option) { \n ";
    String instanceFromClazzCode = funString + instanceFromClazz() + "\n}";
    String classString = "class ARouterMap { \n $mapCode \n $instanceFromClazzCode \n}";
    String fileInputString = import + "\n" + classString;
    return fileInputString;
//    return render(clazzTpl, <String, dynamic>{
//      'refs': refs,
//      'instanceCreated': instanceCreated(),
//      'instanceFromClazz': instanceFromClazz(),
//      'routerMap': collector.routerMap.toString()
//    });
  }
}
