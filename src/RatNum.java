public class RatNum {

    private int numerator;
    private int denominator;

    /**
     * Base case of RatNum, sets numerator to 0 and denominator to 1
     */
    public RatNum(){
        numerator = 0;
        denominator = 1;
    }
    /**
     * Constructs a rational number a/1.
     * @param a numerator
     */
    public RatNum(int a){
        numerator = a;
        denominator = 1;
    }
    /**
     * Constructs a rational number a/b in reduced form.
     * Reduces by gcd (a, b)
     * If one number is negative, makes numerator negative and denominator positive
     * Otherwise both positive
     * @param a numerator
     * @param b denominator
     * @throws NumberFormatException if b == 0
     */
    public RatNum(int a, int b){
        if (b == 0) throw  new  NumberFormatException("Denominator = 0");
        int m = gcd(a, b);
        if (a > 0 && b < 0) {
            a = a/m;
            b = b/m;
            numerator = -a;
            denominator = -b;
        } else if (a < 0 && b < 0) {
            a = a/m;
            b = b/m;
            numerator = -a;
            denominator = -b;
        } else {
            a = a/m;
            b = b/m;
            numerator = a;
            denominator = b;
        }
    }
     /**
     * Produces a new RatNum with the same parameters as the input RatNum
     * @param r RatNum, includes numerator and denominator
     */
    public RatNum(RatNum r){
        this.numerator = r.numerator;
        this.denominator = r.denominator;
    }

    /**
     * Method for getting the numerator of a RatNum
     * @return int for numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Method for getting the denominator of a RatNum
     * @return int for denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Calculates the greatest common divider (GCD) of two integers)
     * The result is always non-negative
     *
     * @param m int for numerator
     * @param n int for denominator
     * @return returns int for greatest common divider between m and n
     * @throws IllegalArgumentException if both m & n = 0
     *
     * <i>Special cases</i>
     * If m = 0, returns n
     * If n = 0, returns m
     */
    public static int gcd(int m, int n) {
        if ((m == 0) && (n == 0)) throw new IllegalArgumentException("Both arguments can't be zero");
        if (m == 0) return n;
        if (n == 0) return m;

        m = Math.abs(m);
        n = Math.abs(n);
        int r = m % n;
        while (r != 0) {
            m = n;
            n = r;
            r = m % n;
        }
        return n;
    }

    /**
     * Converts a RatNum to a String
     * @return string representing a RatNum
     */
    public String toString() {
        return(numerator +"/"+denominator);
    }

    /**
     * Checks if two RatNums are equal.
     * @param r the reference object with which to compare.
     * @return true if they are the same or both numerators and denominators are equal.
     */
    public boolean equals(Object r) {
        if (this == r) return true;
        if (r == null || getClass() != r.getClass()) return false;
        RatNum other = (RatNum) r;
        return this.numerator == other.numerator && this.denominator == other.denominator;
    }

    /**
     * Compares two rational numbers
     * @param r the reference RatNum with which to compare.
     * @return true iff the first rational number < the second rational number
     */
    public boolean lessThan(RatNum r)
    {
        return (this.numerator * r.denominator) < (r.numerator * this.denominator);
    }

    /**
     * Addition between two rational numbers
     * @param r the reference RatNum with which to add.
     * @return new, simplified rational number
     */
    public RatNum add (RatNum r){
        int newNumerator = (this.numerator * r.denominator) + (r.numerator * this.denominator);
        int newDenominator = this.denominator * r.denominator;
        return new RatNum(newNumerator, newDenominator);
    }

    /**
     * Subtraction between two rational numbers
     * @param r the RatNum object with which to subtract.
     * @return new, simplified rational number
     */
    public RatNum sub (RatNum r){
        int newNumerator = (this.numerator * r.denominator) - (r.numerator * this.denominator);
        int newDenominator = this.denominator * r.denominator;
        return new RatNum(newNumerator, newDenominator);
    }

    /**
     * Multiplication between two rational numbers
     * @param r the reference RatNum with which to multiplicate.
     * @return new, simplified rational number
     */
    public RatNum mul (RatNum r){
        int newNumerator = this.numerator * r.numerator;
        int newDenominator = this.denominator * r.denominator;
        return new RatNum(newNumerator, newDenominator);
    }

    /**
     * Division between two rational numbers
     * @param r the reference RatNum with which to divide.
     * @return new, simplified rational number
     */
    public RatNum div (RatNum r){
        int newNumerator = this.numerator * r.denominator;
        int newDenominator = this.denominator * r.numerator;
        return new RatNum(newNumerator, newDenominator);
    }
}