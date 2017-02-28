
## QUnit

* Is created by the JQuery team
* Is a standalone test framework that runs on the browser
* Likes a lot on JUnit

```javascript
QUnit.test( "My First Test", function( assert ) {
    var value = "1";
    assert.equal( value, "1", "Value should be 1" );
});
```