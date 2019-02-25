package dragonBallZ.model

case class Team(name: String, warriors: List[Warrior]) extends Group with Fighter {
  val energy: Int  = warriors.map(_.energy).sum

  override def fight(vs: Fighter): Option[Team] =
    Option(this.substractEnergy(vs)).filter(_.energy > 0)

  override def substractEnergy(vs: Fighter): Team = this.warriors match {
    case Nil  => this
    case h::t => this.copy(warriors = h.fight(vs).map(_::t).getOrElse(t))
  }

  override def fighters: List[Warrior] = this.warriors
}
