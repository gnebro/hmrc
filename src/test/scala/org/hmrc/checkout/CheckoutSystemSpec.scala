package org.hmrc.checkout

import org.hmrc.checkout.model.{Fruit, Orange, Apple}
import org.scalatest.{BeforeAndAfter, Matchers, FlatSpec}

/**
  * Unit test for CheckoutSystem
  */
class CheckoutSystemSpec extends FlatSpec with Matchers with BeforeAndAfter {

  import TestFixture._
  import CheckoutSystem.basketAmount

  "basketAmount " should " return zero when the basket is empty" in {
    val totAmount = basketAmount(List.empty[Fruit])
    totAmount shouldBe 0.0
  }

  "basketAmount " should " return the right price for a non empty basket" in {
    val totAmount = basketAmount(FullBasket)
    totAmount shouldBe FullBasket_Price
  }

}

object TestFixture {
  val FullBasket = Orange :: Apple :: Apple :: Nil
  val FullBasket_Price = Orange.price + Apple.price + Apple.price
}
