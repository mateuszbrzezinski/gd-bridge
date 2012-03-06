package pl.awesome.grails.plugins.gdbridge

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GdService)
class GdServiceTests {

    void testActive() {
        def gdService = new GdService()


        assert gdService.isActive()
    }

    void testGetInfo(){
        def gdService = new GdService()

        def file = getClass().getResource('ikona.png')
        
        assert file
        
        def info =  gdService.getInfo(file.getPath())

        assert info.width == 140
        assert info.height == 142
        assert info.mime == 'image/png'
    }
    
    void testScriptPath() {
        def gdService = new GdService()

        assert gdService.getScriptPath()
    }
}
