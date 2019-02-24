package dragonBallZ.model

case class Mision(name: String, activities: List[Fighter]) extends Group with Fighter {
  val energy: Int  = activities.map(_.energy).sum

  def substractEnergy(vs: Fighter): Mision = this.activities match {
    case Nil  => this
    case h::t => this.copy(activities = h.fight(vs).map(_::t).getOrElse(t))
  }
  override def getFighters: List[Fighter] = this.activities
}
