package org.hmrc.checkout

import org.hmrc.checkout.AvailableOffers._
import org.hmrc.checkout.model.{Fruit, Orange, Apple}
import org.scalatest.{BeforeAndAfter, Matchers, FlatSpec}

/**
  * Unit test for CheckoutSystem
  */
class CheckoutSystemSpec extends FlatSpec with Matchers with BeforeAndAfter {

  import TestFixture._
  import CheckoutSystem._

  "basketAmount " should " return zero when the basket is empty with no offers" in {
    val totAmount = basketAmount(List.empty[Fruit])()
    totAmount shouldBe 0.0
  }

  "basketAmount " should " return zero when the basket is empty and some offer is applied" in {
    val totAmount = basketAmount(List.empty[Fruit])(offerOnOranges :: offerOnApples :: Nil)
    totAmount shouldBe 0.0
  }

  "basketAmount with 3 items" should " return the right price with no offers" in {
    val totAmount = basketAmount(FullBasket_3items)()
    totAmount shouldBe FullBasket_3items_Price
  }

  "basketAmount with 3 items" should " return the right price with offer on oranges" in {
    val totAmount = basketAmount(FullBasket_3items)(offerOnOranges :: Nil)
    totAmount shouldBe FullBasket_3items_Price
  }

  "basketAmount with 3 items" should " return the right price with offer on apples" in {
    val totAmount = basketAmount(FullBasket_3items)(offerOnApples :: Nil)
    totAmount shouldBe FullBasket_3items_AppleOffer_Price
  }

  "basketAmount with 3 items" should " return the right price with offer on apples and oranges" in {
    val totAmount = basketAmount(FullBasket_3items)(offerOnApples :: offerOnOranges :: Nil)
    totAmount shouldBe FullBasket_3items_AppleOffer_Price
  }

  "basketAmount with 9 items" should " return the right price with no offers" in {
    val totAmount = basketAmount(FullBasket_9items)()
    totAmount shouldBe FullBasket_9items_Price
  }

  "basketAmount with 9 items" should " return the right price with offer on oranges" in {
    val totAmount = basketAmount(FullBasket_9items)(offerOnOranges :: Nil)
    totAmount shouldBe FullBasket_9items_OrangeOffer_Price
  }

  "basketAmount with 9 items" should " return the right price with offer on apples" in {
    val totAmount = basketAmount(FullBasket_9items)(offerOnApples :: Nil)
    totAmount shouldBe FullBasket_9items_AppleOffer_Price
  }

  "basketAmount with 9 items" should " return the right price with offer on apples and oranges" in {
    val totAmount = basketAmount(FullBasket_9items)(offerOnApples :: offerOnOranges :: Nil)
    totAmount shouldBe FullBasket_9items_BothOffers_Price
  }

}

object TestFixture {

  val EmptyBasket = List.empty[Fruit]
  val FullBasket_3items = Orange :: Apple :: Apple :: Nil
  val FullBasket_3items_Price = round(Orange.price + Apple.price + Apple.price)
  val FullBasket_3items_AppleOffer_Price = round(Orange.price + Apple.price)

  val FullBasket_9items = Orange :: Apple :: Apple :: Apple :: Orange :: Orange :: Orange :: Apple :: Apple :: Nil
  val FullBasket_9items_Price = round(Orange.price + Apple.price + Apple.price + Apple.price + Orange.price + Orange.price + Orange.price + Apple.price + Apple.price)
  val FullBasket_9items_AppleOffer_Price = round(Orange.price + Apple.price + Apple.price + Orange.price + Orange.price + Orange.price + Apple.price)
  val FullBasket_9items_OrangeOffer_Price = round(Orange.price + Apple.price + Apple.price + Apple.price + Orange.price + Orange.price + Apple.price + Apple.price)
  val FullBasket_9items_BothOffers_Price = round(Orange.price + Apple.price + Apple.price + Orange.price + Orange.price + Apple.price)

  def round(amount: Double): Double = BigDecimal(amount).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
}
