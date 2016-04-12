package org.hmrc.checkout.model

/**
  * Created by AnsaloniL on 12/04/2016.
  */
sealed trait Fruit {
  def price: Double
}

object Orange extends Fruit {
  override def price: Double = 0.25
}

object Apple extends Fruit {
  override def price: Double = 0.60
}

