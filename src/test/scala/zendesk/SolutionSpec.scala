package zendesk

import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import zendesk.Solution._

class SolutionSpec extends AnyWordSpec with ScalaCheckPropertyChecks {
  "validLength" should {
    "return true given string is between MIN_LENGTH and MAX_LENGTH inclusive" in {
      forAll { str: String =>
        if (str.length >= MIN_LENGTH && str.length <= MAX_LENGTH) {
          assert(validLength(str))
        } else assert(!validLength(str))
      }
    }
    "return false given string is less than MIN_LENGTH" in {
      forAll { str: String =>
        if (str.length < MIN_LENGTH) {
          assert(!validLength(str))
        }
      }
    }
    "return false given string is greater than MAX_LENGTH" in {
      forAll { str: String =>
        if (str.length > MAX_LENGTH) {
          assert(!validLength(str))
        }
      }
    }
  }

  "validSum" should {
    "return false given credit card string is 4408041234567893" in {
      val creditCard = "4408041234567893"
      assert(validSum(creditCard))
    }

    "return false given credit card string is 4408041234567894" in {
      val creditCard = "4408041234567894"
      assert(!validSum(creditCard))
    }
  }

  "validPrefix" should {
    "return true if given string start with 4" in {
      val creditCard = "4408041234567893"
      assert(validPrefix(creditCard))
    }
    "return true if given string start with 5" in {
      val creditCard = "5408041234567893"
      assert(validPrefix(creditCard))
    }
    "return true if given string start with 37" in {
      val creditCard = "3708041234567893"
      assert(validPrefix(creditCard))
    }
    "return true if given string start with 6" in {
      val creditCard = "6408041234567893"
      assert(validPrefix(creditCard))
    }
    "return false if given string doesn't start with valid prefix" in {
      forAll { str: String =>
        if (!str.startsWith("4") && !str.startsWith("5") && !str.startsWith("6") && !str.startsWith("37")) {
          assert(!validPrefix(str))
        }
      }
    }
  }

  "validCreditCard" should {
    "return true given credit 4408041234567893" in {
      val creditCard = "4408041234567893"
      assert(validCreditCard(creditCard))
    }
    "return true given credit 378282246310005" in {
      val creditCard = "378282246310005"
      assert(validCreditCard(creditCard))
    }
    "return false given credit 440804123456789300 has valid sum and prefix but invalid length" in {
      val creditCard = "440804123456789300"
      assert(!validCreditCard(creditCard))
    }
    "return false given credit 4408041234567894 has valid length and prefix but invalid sum" in {
      val creditCard = "4408041234567894"
      assert(!validCreditCard(creditCard))
    }
    "return false given credit 4408041234567894 has valid length and sum but invalid prefix" in {
      val creditCard = "2408041234567897"
      assert(!validCreditCard(creditCard))
    }
  }
}
