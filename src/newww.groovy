import groovy.json.JsonBuilder

import java.time.LocalDateTime

class newww {

    static void main (String [] args){


        HashMap dadMap = new HashMap();
        dadMap.put("age", 35.3)
        dadMap.put("name", "Sergey")

        List auto = ["bmw", "mercedes"]

        HashMap son = new HashMap()
        son.put("name", "Vaska")
        son.put("age", 8.6)
        List gameSon = ["Gta", "CoD"]
        son.put("game", gameSon)

        HashMap daughter = new HashMap()
        daughter.put("name", "Masha")
        daughter.put("age", 10.1)
        List gameDaughter = ["Sims"]
        daughter.put("game", gameDaughter)

        List children = new ArrayList()
        children.add(son)
        children.add(daughter)

        dadMap.put("wives", true)

        def otherInfo = [hous: 233.1, work: "GreenData"]

        dadMap.put("children",children)
        dadMap.put("auto", auto)
        dadMap.put("other", otherInfo)
        dadMap.put("test", null)
        dadMap.put("test2", LocalDateTime.now().format("dd-mm-yyyy"))
        dadMap.put("test3", LocalDateTime.now())




        def check = dadMap
        println(new JsonBuilder(check).toPrettyString())
        println(MyJson.toJson(check))

    }

}
