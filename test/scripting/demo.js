
addTests(

function(){}, // setup
function(){}, // teardown
{
  deliberatelyFail: function () {
    return "i suck";
  },
  triviallyPass: function() {
  },
  useTrace: function(trace) {
    trace("here I am!");
    return undefined;
  }
});
