package pl.awesome.grails.plugins.gdbridge

import grails.converters.JSON


class GdService {

    public static void main(String[] args) {
        def gd = new GdService()
        println gd.getInfo('/Users/brzezi/Documents/IdeaProjects/gd-bridge/test/unit/pl/awesome/grails/plugins/gdbridge/ikona.png')
    }

    def getInfo(String file){
        def ret = runScript(["--getinfo=$file"])
        return ret.value
    }
    
    def isActive() {
        def ret = runScript(['--active'])
        return ret.value
    }

    protected def runScript(def args){
        def cmd = ['php', getScriptPath()]
        cmd.addAll(args)

        Process process = cmd.execute()
        def exit = process.waitFor()

        if(exit != 0){
            throw new Exception("Proces error exit, ${process.text}")
        }

        def value = process.text
        
//        println "value: $value"

        return JSON.parse(value)
    }

    def getScriptPath(){
        getClass().getResource('php/gdrun.php').getPath()

//        return '/Users/brzezi/Documents/IdeaProjects/gd-bridge/src/groovy/pl/awesome/grails/plugins/gdbridge/php/gdrun.php'
    }
}
