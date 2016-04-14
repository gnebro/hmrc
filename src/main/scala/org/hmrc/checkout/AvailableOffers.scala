package org.hmrc.checkout

import org.hmrc.checkout.model.{Apple, Orange, Fruit}

object AvailableOffers {

  type Offer = List[Fruit] => Double

  val offerOnOranges: Offer = (basket) => {
    val oranges = basket.collect{case o @ Orange => o}.size
    (oranges / 3 * Orange.price)
  }

  val offerOnApples: Offer = (basket) => {
    val apples = basket.collect{case a @ Apple => a}.size
    (apples / 2 * Apple.price)
  }

}
