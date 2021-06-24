
trait rightCommands {
  var cmd:String
}

object rCup extends rightCommands {
  override var cmd = "w"
}
object rCdown extends rightCommands {
  override var cmd = "s"
}
object rCleft extends rightCommands {
  override var cmd = "a"
}
object rCright extends rightCommands {
  override var cmd = "d"
}

val direcs = Vector("w","a","s","d")
val input = "w"

input match {
  case x if direcs.contains(input) => "yes" + input
  case _ => "no"
}