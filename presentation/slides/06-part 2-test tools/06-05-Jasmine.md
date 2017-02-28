
## Jasmine

* BDD test framework
* Influenced by RSpec en JSpec
* Is maintained by Pivotal Labs
* Runs on NodeJs and in the browser
    * Uses the 'jasmine-node' npm package to run on nodeJs

```javascript
describe("Hello World", function(){ 
   
   it("should Return Hello world",function(){ 
      expect(helloworld()).toEqual('Hello World'); 
   }); 

});
```