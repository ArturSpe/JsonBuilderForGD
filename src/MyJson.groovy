import org.codehaus.groovy.runtime.NullObject

import java.time.LocalDate
import java.time.LocalDateTime

class MyJson<T> {

    static String toJson(collection){

        def jsonBody = ''
        def iterationNumber = 0
        def size = collection.size()
        def classCollection = collection.getClass()
        def classIteration = String
        def iterator = []
        def value;
        def entryKey;

        if(classCollection != HashMap && classCollection != ArrayList
                && classCollection != LinkedHashMap && classCollection != LinkedList){return "illegalArgumentException"}

        if (classCollection == HashMap || classCollection == LinkedHashMap){
            iterator = collection.entrySet()
        }

        else if (classCollection == LinkedList || classCollection == ArrayList){
            iterator = collection
        }

        for(def entry : iterator){
            if (classCollection == HashMap || classCollection == LinkedHashMap){
                value = entry.getValue()
                entryKey = entry.getKey()
            }
            else if (classCollection == LinkedList || classCollection == ArrayList){
                value = entry
            }


            if(value.getClass()  !=  HashMap  &&  value.getClass()  !=  ArrayList &&
                    value.getClass() != String && value.getClass() != Boolean &&
                    value.getClass() != LinkedHashMap && value.getClass() != LinkedList &&
                    value.getClass() != Integer && value.getClass() != Long &&
                    value.getClass() != BigDecimal && value.getClass() != Double &&
                    value.getClass() != LocalDateTime && value.getClass() != LocalDate &&
                    value.getClass() != NullObject && value != null){
                return  "IllegalArgumentException"
            }

            iterationNumber++

            if (value.getClass() == ArrayList || value.getClass() == HashMap ||
                    value.getClass() == LinkedList || value.getClass() == LinkedHashMap){
                def recursionResult = toJson(value)
                if (classCollection == HashMap || classCollection == LinkedHashMap){jsonBody = jsonBody + "\"" + entryKey + "\":" + recursionResult}
                else if (classCollection == LinkedList || classCollection == ArrayList){jsonBody = jsonBody + recursionResult}

            }
            if (value.getClass() == NullObject){
                value = null
            }
            if (value.getClass() == Integer || value.getClass() == Long ||
                    value.getClass() == BigDecimal || value.getClass() == Double ||
                    value == null || value.getClass()  ==  Boolean ||
                    value.getClass() == NullObject){
                if (value.getClass() == NullObject){
                    value = null
                }
                if (classCollection == HashMap || classCollection == LinkedHashMap){jsonBody = jsonBody + '"' + entryKey + '":' + value}
                else if (classCollection == LinkedList || classCollection == ArrayList){jsonBody = jsonBody + value}

            }else if(value.getClass()  ==  String ||
                    value.getClass() == LocalDateTime || value.getClass() == LocalDate){
                if (value.getClass() == LocalDateTime || value.getClass() == LocalDate){
                    value.toString()
                }
                if (classCollection == HashMap || classCollection == LinkedHashMap){jsonBody = jsonBody  +  '"'  +  entryKey  +  "\":\""  +  value + '"'}
                else if (classCollection == LinkedList || classCollection == ArrayList){jsonBody  = jsonBody + "\""  +  value + '"'}
            }

            if (iterationNumber != size){
                jsonBody = jsonBody + ','
            }
        }

        if (classCollection == HashMap || classCollection == LinkedHashMap){jsonBody = '{' + jsonBody + '}'}
        else if (classCollection == LinkedList || classCollection == ArrayList){jsonBody  =  '['  +  jsonBody  +  ']'}

        return jsonBody
    }

}
