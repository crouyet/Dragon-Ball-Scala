package dragonBallZ.model

case class Team(name: String, warriors: List[Warrior]) extends Group {
  val energy: Int  = warriors.map(_.energy).sum

  def movementToUse: Option[Movement] = warriors.head.movementToUse

  override def fight(energy: Int): Option[Team] =
    Option(this.substractEnergy(energy)).filter(_.energy > 0)

  override def substractEnergy(energy: Int): Team = this.warriors match {
    case Nil  => this
    case h::t => this.copy(warriors = h.fight(energy).map(_::t).getOrElse(t))
  }

  override def fighters: List[Warrior] = this.warriors

  override def toString: String = name +": " + fighters.map(f => f.name + ": life -> " + f.lifePoints + " | energy -> " + f.energy)
}
