
// Our own little unit test framework for javascript

// TO USE:
// create a javascript file that invokes addTests or addSuite.
// The testmap argument should take a map of test names to functions.
// The functions may optionally take a function argument that will
// print trace output.  The test functions should return a false value
// (not invoking return at all should do this) on success, or a message
// on failure.
//
// runTests, addTests, and addSuite should be maintained such that they
// assume no particular invocation environment (browser, command line,
// Java-based unit test framework, whatever).
// This is so the same body of tests could be used in different environments.

var _tests = [];

function addSuite(m) {
  _tests.push(m);
}

function addTests(setupFunction, teardownFunction, testmap) {
  addSuite({
    setup: setupFunction,
    teardown: teardownFunction,
    tests: testmap
  });
}


function runTestsBrowser()
{
    var stats = runTests(
	function(testName){console.log("SUCCESS " + testName);},
	function(testName, msg){console.log("FAILURE " + testName + ": " + msg);},
	function(testName, msg){console.log("ERROR " + testName + ": " + msg);},
	function(msg){console.log("TRACE " + msg);}
    );
    console.log("Results = " + JSON.stringify(stats));
}


function runTestsConsole() {
    var stats = runTests(
        // success printer
        function(x) { print("."); },
        // failure printer
        function(name, why) { println(name + " FAILED: " + why); },
        // error printer
        function(name, exc) {
            println(name + " ERROR!");
            if (typeof(exc) == "string") {
                println("    msg: " + exc);
            } else {
                // try typical exception fields
                foreach(function (field) {
                    if (exc[field]) {
                        println("    " + field + ": " + exc[field]);
                    }
                }, ['message', 'name', 'fileName', 'lineNumber']);
                // The printStackTrace() method on that this exception object
                // will show the full stack, including
                // the script frames, but there seems to be no easy way to 
                // obtain the script stack data easily programmatically.
                // Thus this kluge:
                if (exc['rhinoException']) {
                    println("    stack:");
                    var sb = new java.io.StringWriter();
                    exc['rhinoException'].printStackTrace(new java.io.PrintWriter(sb));
                    var lines = map(function(line) {
                        return line.substring(10);
                    }, filter (function (line) {
                        return line.length() > 10 && line.substring(0, 10) == "\tat script";
                    }, sb.toString().split("\n")));
                    println("\t" + lines.join("\n\t"));
                }
            }
        },
        // trace printer
        function(x) { println("TRACE: " + x); }
    );
    println("done");
    println("Successes: " + stats.successes);
    println("Failures: " + stats.failures);
    println("Errors: " + stats.errors);
}


/**
 * Runs registered tests using the given functions.
 * @param successCallback a function called on successes, whose single argument
 *        is the name of the test.
 * @param failureCallback a function called on failures, whose two arguments
 *        are the name of the test and the result (return value)
 *        of the test.
 * @param errorCallback a function called on errors (exceptions being thrown)
 *        whose two arguments are the name of the test and the exception.
 *        Note that the exception might be a special exception object,
 *        or a string, or whatever something chose to throw.
 * @param traceCallback a function passed to the test.  The test might call
 *        call this function with a single textual argument.
 */
function runTests(successCallback, failureCallback, errorCallback, traceCallback) {
  var stats = {
    successes: 0,
    failures: 0, 
    errors: 0
  };
  for(var suiteIdx in _tests) {
    for (var testName in _tests[suiteIdx].tests) {
      _tests[suiteIdx].setup();
      try {
        var result = _tests[suiteIdx].tests[testName](traceCallback);
        if (!result) {
          // a null/empty/undefined/false result means success!
          successCallback(testName);
          stats.successes++;
        } else {
          failureCallback(testName, result);
          stats.failures++;
        }
      } catch(exc) {
        errorCallback(testName, exc);
        stats.errors++;
      }
      _tests[suiteIdx].teardown();
    }
  }
  return stats;
}


/**
 * Produces a list of Java objects, convenient for running from TestNG.
 * CAUTION: this assumes that certain classes are on the path,
 * and probably will fail in any scenario other than the one it was
 * defined for.
 */
function getTestsForTestNG() {
  var suitesList = new java.util.ArrayList();
  for(var suiteIdx in _tests) {
    var suite = java.lang.Class.forName("com.weaselworks.scripting.JavascriptUnitTestRunner$JavascriptSuite").newInstance();
    suite.setupFunction = _tests[suiteIdx].setup;
    suite.teardownFunction = _tests[suiteIdx].teardown;
    for (var testName in _tests[suiteIdx].tests) {
      var test = java.lang.Class.forName("com.weaselworks.scripting.JavascriptUnitTestRunner$JavascriptTest").newInstance();
      test.name = testName;
      test.testFunction = _tests[suiteIdx].tests[testName];
      suite.tests.add(test);
    }
    suitesList.add(suite);
  }
  return suitesList;
}

