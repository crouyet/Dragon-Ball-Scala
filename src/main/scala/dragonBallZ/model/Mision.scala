package dragonBallZ.model

case class Mision(name: String, dangerousness: Int, activities: List[Activity]) extends Fighter[Mision] {
  val energy: Int  = activities.map(_.energy).sum

  override def substractEnergy(vs: Int): Mision = this.activities match {
    case Nil  => this
    case h::t => this.copy(activities = h.fight(vs).map(_::t).getOrElse(t))
  }
}
