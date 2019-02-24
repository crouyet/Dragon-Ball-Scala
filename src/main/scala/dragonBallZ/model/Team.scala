package dragonBallZ.model

case class Team(name: String, warriors: List[Fighter]) extends Group with Fighter {
  val energy: Int  = warriors.map(_.energy).sum

  def substractEnergy(vs: Fighter): Team = this.warriors match {
    case Nil  => this
    case h::t => this.copy(warriors = h.fight(vs).map(_::t).getOrElse(t))
  }

  override def getFighters: List[Fighter] = this.warriors
}
