package org.hmrc.checkout

import org.hmrc.checkout.model.Fruit

object CheckoutSystem {

  def basketAmount(basket: List[Fruit]): Double = basket.foldLeft(0.0)((acc, fruit) => acc + fruit.price)

}
