describe("Calculator", function () {
  describe("when addition is performed", function () {
    
    var calculator;

    beforeEach(function () {
      calculator = new Calculator();
    });

    it("should return 5 when 3 is added to 2", function () {
      expect(calculator.add(3, 2)).toEqual(5);
    });

    it("should return 20 when 10 is added to 10", function () {
      expect(calculator.add(10, 10)).toEqual(20);
    });
  });
});
