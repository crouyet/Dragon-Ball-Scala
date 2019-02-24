println({
  var x = 1+1
  x +1
})

class Point {
  private var _x = 0
  private var _y = 0
  private val bound = 100

  def x = _x
  def x_= (newValue: Int): Unit = {
    if (newValue < bound) _x = newValue else printWarning
  }

  def y = _y
  def y_= (newValue: Int): Unit = {
    if (newValue < bound) _y = newValue else printWarning
  }

  private def printWarning = println("WARNING: Out of bounds")
}

val point1 = new Point
point1.x = 99
point1.y = 101 // prints the warning

class Point2(val x: Int, val y: Int)
val point2 = new Point2(1, 2)
point2.x

case class Point3(var x: Int, y: Int)
var point3 = new Point3(1, 2)
var point4 = new Point3(1, 2)
point3.x  // <-- does not compile

point4 == point3

