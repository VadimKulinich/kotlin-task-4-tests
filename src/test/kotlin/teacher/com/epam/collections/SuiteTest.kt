package teacher.com.epam.collections

import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.runners.Suite
import java.io.File
import java.io.IOException
import java.util.*


@RunWith(Suite::class)
@Suite.SuiteClasses(TestArrayListTest::class)
class SuiteTest {


    companion object {
        @BeforeClass
        @Throws(IOException::class)
        fun setUp() {
            //todo
           /* val file = File(TestUtils.FILE_ADDRESS)
            TestUtils.checkForbiddenImports(file)
            val files: MutableList<File?> = TestUtils.getAllFiles(file)
            TestUtils.checkFileAmount(files)
            val requiredFileNames: MutableList<String?> =
                ArrayList()
            requiredFileNames.add("TestArrayListTest.kt")
            TestUtils.checkFilePresence(files, requiredFileNames)*/
        }
    }

}