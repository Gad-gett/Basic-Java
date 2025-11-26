import java.math.BigInteger;

public class RatNum {

    private BigInteger numerator;
    private BigInteger denominator;

    private RatNum(BigInteger n, BigInteger d){
        if (d == BigInteger.ZERO) throw  new  NumberFormatException("Denominator = 0");
        BigInteger m = n.gcd(d);
        if (d.max(BigInteger.ZERO) == BigInteger.ZERO){
            n = n.divide(m);
            d = d.divide(m);
            numerator = n.negate();
            denominator = d.negate();
        } else {
            n = n.divide(m);
            d = d.divide(m);
            numerator = n;
            denominator = d;
        }
    }

    /**
     * Base case of RatNum, sets numerator to 0 and denominator to 1
     */
    public RatNum(){
        numerator = BigInteger.ZERO;
        denominator = BigInteger.ONE;
    }
    /**
     * Constructs a rational number a/1.
     * @param n numerator
     */
    public RatNum(int n){
        numerator = BigInteger.valueOf(n);
        denominator = BigInteger.ONE;
    }
    /**
     * Constructs a rational number a/b in reduced form.
     * Reduces by gcd (a, b)
     * If one number is negative, makes numerator negative and denominator positive
     * Otherwise both positive
     * @param n numerator
     * @param d denominator
     * @throws NumberFormatException if b == 0
     */
    public RatNum(int n, int d){
        this(BigInteger.valueOf(n), BigInteger.valueOf(d));
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
     * @return converts BigInt to int and returns as numerator
     */
    public int getNumerator() {
        return numerator.intValue();
    }

    /**
     * Method for getting the denominator of a RatNum
     * @return converts BigInt to int and returns as denominator
     */
    public int getDenominator() {
        return denominator.intValue();
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
        return this.numerator.equals(other.numerator) && this.denominator.equals(other.denominator);
    }

    /**
     * Compares two rational numbers
     * @param r the reference RatNum with which to compare.
     * @return true iff the first rational number < the second rational number
     */
    public boolean lessThan(RatNum r)
    {
        return (this.numerator.multiply(r.denominator)).compareTo(r.numerator.multiply(this.denominator)) < 0;
    }

    /**
     * Addition between two rational numbers
     * @param r the reference RatNum with which to add.
     * @return new, simplified rational number
     */
    public RatNum add (RatNum r){
        BigInteger newNumerator = (this.numerator.multiply(r.denominator)).add(r.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(r.denominator);
        return new RatNum(newNumerator, newDenominator);
    }

    /**
     * Subtraction between two rational numbers
     * @param r the RatNum object with which to subtract.
     * @return new, simplified rational number
     */
    public RatNum sub (RatNum r){
        BigInteger newNumerator = (this.numerator.multiply(r.denominator)).subtract(r.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(r.denominator);
        return new RatNum(newNumerator, newDenominator);
    }

    /**
     * Multiplication between two rational numbers
     * @param r the reference RatNum with which to multiplicate.
     * @return new, simplified rational number
     */
    public RatNum mul (RatNum r){
        BigInteger newNumerator = (this.numerator.multiply(r.numerator));
        BigInteger newDenominator = this.denominator.multiply(r.denominator);
        return new RatNum(newNumerator, newDenominator);
    }

    /**
     * Division between two rational numbers
     * @param r the reference RatNum with which to divide.
     * @return new, simplified rational number
     */
    public RatNum div (RatNum r){
        BigInteger newNumerator = (this.numerator.multiply(r.denominator));
        BigInteger newDenominator = this.denominator.multiply(r.numerator);
        return new RatNum(newNumerator, newDenominator);
    }
    /**
     * Integer part extraction for rational numbers.
     * @return the integer part of this rational number
     */
    public int intPart() {
        return numerator.divide(denominator).intValue();
    }


    /**
     * Power funktion for rational numbers.
     * If k = 0, returns 1/1.
     * @param k the reference int which is used as the exponent
     * @return new, simplified, rational number that has been raised to the power of k.
     */
    public RatNum pow(int k) {
        if (k == 0){
            return new RatNum (1,1);
        }else {
            BigInteger newNumerator = this.numerator.pow(k);
            BigInteger newDenominator = this.denominator.pow(k);
            return new RatNum(newNumerator, newDenominator);
        }
    }
}