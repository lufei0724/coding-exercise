### Some improvements made

- Remove magic number in valid length function
```scala
 def validLength(creditCard: String): Boolean =
   !(creditCard.length < MIN_LENGTH || creditCard.length > MAX_LENGTH)
``` 

- Use foldRight in valid sum function, so only need to iterate the string once
```scala
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
```

- Use property-based test for testing validLength and validPrefix function
