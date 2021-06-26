package de.htwg.se.malefiz.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test-Klasse für unsere Observable Klasse.
 *
 *  @author sehirsig & franzgajewski
 */
class ObservableSpec extends AnyWordSpec with Matchers {
  "An Observable" should {
    val observable = new Observable
    val observer = new Observer {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update: Boolean = {updated = true; updated}
    }
    "Add an Observer" in {
      observable.add(observer)
      observable.subscribers should contain (observer)
    }
    "notify an Observer" in {
      observer.isUpdated should be(false)
      observable.notifyObservers
      observer.isUpdated should be (true)
    }
    "remove an Observer" in {
      observable.remove(observer)
      observable.subscribers should not contain (observer)
    }
  }
}