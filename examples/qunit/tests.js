QUnit.module("Module A", {
  beforeEach: function (assert) {
    assert.ok(true, "before test case");
  }, afterEach: function (assert) {
    assert.ok(true, "after test case");
  }
});
QUnit.test("test case 1", function (assert) {
  assert.ok(true, "Module A: in test case 1");
});
QUnit.test("test case 2", function (assert) {
  assert.ok(true, "Module A: in test case 2");
});

QUnit.module("Module B");
QUnit.test("test case 1", function (assert) {
  assert.ok(true, "Module B: in test case 1");
});
QUnit.test("test case 2", function (assert) {
  assert.ok(true, "Module B: in test case 2");
});