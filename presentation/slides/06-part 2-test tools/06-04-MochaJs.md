
## MochaJs

* BDD test framework
* Is a standalone test framework
* Has no assert and mock framework
    * [ChaiJs](http://chaijs.com/) can be used as an assert framework
    * [SinosJs](http://sinonjs.org/) can be used as an mock framework
* Runs on NodeJs and in the browser

```javascript
// Test shall run on NodeJs
var assert = require('assert');
describe('Array', function() {
  describe('#indexOf()', function() {
    it('should return -1 when the value is not present', function() {
      assert.equal(-1, [1,2,3].indexOf(4));
    });
  });
});
```