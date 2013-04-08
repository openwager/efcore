
package com.weaselworks.scripting;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.Writer;

import java.util.LinkedList;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.SimpleScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static org.testng.Assert.fail;
import org.testng.ITest;
import org.testng.Reporter;
import org.testng.TestException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class JavascriptUnitTestRunner {

    @Parameters({"libraryFiles", "testFiles"})
    @Factory
    public Object[] makeTests(String libraryFiles, String testFiles) {
        try {
            ScriptEngine e = new ScriptEngineManager().getEngineByName("JavaScript");
            for(String s : libraryFiles.split(",\\s*")) {
                e.eval(new FileReader(s));
            }
            for(String s : testFiles.split(",\\s*")) {
                e.eval(new FileReader(s));
            }

            List<JavascriptSuite> suites
                = (List<JavascriptSuite>) e.eval("getTestsForTestNG()");
            List<Object> testNGtests = new LinkedList<Object>();

            for (JavascriptSuite suite : suites) {
                for (JavascriptTest test : suite.tests) {
                    ScriptContext testContext = new SimpleScriptContext();
                    Bindings b = testContext.getBindings(ScriptContext.ENGINE_SCOPE);
                    b.put("test", test.testFunction);
                    b.put("setup", suite.setupFunction);
                    b.put("teardown", suite.teardownFunction);
                    // as far as I can tell, this does nothing.
                    // I thought it might redirect standard out.
                    testContext.setWriter(new Writer() {
                        public void  close() {} // noop
                        public void  flush() {} // noop
                        public void  write(char[] cbuf, int off, int len) {
                            Reporter.log(new String(cbuf, off, len));
                        }
                    });
                    JavascriptUnitTest unitTest
                        = new JavascriptUnitTest(test.name, b, e);
                    testNGtests.add(unitTest);
                }
            }

            return testNGtests.toArray();

        // The idea wrapping and rethrowing the exception with a
        // TestException, was that I had hoped that TestNG might
        // produce more useful error output than simply doing almost
        // nothing.  This doesn't seem to be the case.  But leaving this
        // code in because it seems just as good as any other way of handling
        // these exceptions.  Perhaps you can think of a better way?
        } catch (ScriptException t) {
            t.printStackTrace();
            throw new TestException(t);
        } catch (FileNotFoundException t) {
            t.printStackTrace();
            throw new TestException(t);
        }
    }

    public class JavascriptUnitTest implements ITest {

        private String name;
        private Bindings env;
        private ScriptEngine eng;

        public JavascriptUnitTest(String name, Bindings b, ScriptEngine e) {
            this.name = name;
            this.env = b;
            this.eng = e;
        }

        @BeforeMethod
        public void setup() throws ScriptException {
            eng.eval("setup()", env); // TODO check for null first?
        }

        @Test(groups={"JavascriptTests"})
        public void runTest() throws ScriptException {
            Object result = eng.eval("test(println)", env);
            if (result != null) {
                fail(result.toString());
            }
        }

        @AfterMethod
        public void tearDown() throws ScriptException {
            eng.eval("teardown()", env); // TODO check for null first?
        }

        public String getTestName() {
            return name;
        }
    }

    public static class JavascriptSuite {
        public Object setupFunction;
        public Object teardownFunction;
        public List<JavascriptTest> tests;

        public JavascriptSuite() {
            tests = new LinkedList<JavascriptTest>();
        }
    }

    public static class JavascriptTest {
        public String name;
        public Object testFunction;
    }
}

