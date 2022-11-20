package zendesk

object Solution {
  /*
  A credit card number must have between 13 and 16 digits, inclusive.

  Credit card numbers can be validated with a process called the Luhn algorithm. The Luhn algorithm works like this:
  1. Starting with the second last digit and continuing with every second digit going back to the beginning of the card, double the digit.
  2. Sum all doubled and untouched digits in the number.
  3. If that total is a multiple of 10, the number is valid.

  For example, given the card number 4408041234567893:
  Orig :  4 4 0 8 0 4 1 2 3 4 5  6 7  8 9  3
  Step 1: 8 4 0 8 0 4 2 2 6 4 10 6 14 8 18 3
  Step 2: 8+4+0+8+0+4+2+2+6+4+1+0+6+1+4+8+1+8+3 = 70
  Step 3: 70 % 10 == 0

  Credit card numbers must start with certain numbers:
  4 for Visa cards
  5 for Master cards
  37 for American Express cards
  6 for Discover cards

*/

  val MIN_LENGTH = 13
  val MAX_LENGTH = 16

  def validCreditCard(creditCard: String): Boolean = {
    validLength(creditCard) && validSum(creditCard) && validPrefix(creditCard)
  }

  def validLength(creditCard: String): Boolean =  !(creditCard.length < MIN_LENGTH || creditCard.length > MAX_LENGTH)

  def validPrefix(creditCard: String): Boolean = creditCard.matches("^[456].*|^37.*")

  def validSum(creditCard: String): Boolean = {
    val sum = creditCard.foldRight[(Int, Int)]((0, 1)) {
      case (elem, (sum, index)) =>
        if (index % 2 != 0) (sum + elem.asDigit, index + 1)
        else {
          val sumNewElem = (elem.asDigit * 2).toString.split("").toList.map(_.toInt).sum
          (sum + sumNewElem, index + 1)
        }
    }._1

    sum % 10 == 0
  }
}
