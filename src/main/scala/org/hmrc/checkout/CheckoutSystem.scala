package org.hmrc.checkout

import org.hmrc.checkout.model.Fruit
import org.hmrc.checkout.AvailableOffers.Offer

object CheckoutSystem {

  def basketAmount(basket: List[Fruit])(offers: List[Offer] = List.empty[Offer]): Double = {
    val fullPrice = basket.foldLeft(0.0)((acc, fruit) => acc + fruit.price)
    if(offers.isEmpty)fullPrice
    else offers.foldLeft(fullPrice)((acc, offer) => acc - offer(basket))
  }

}
