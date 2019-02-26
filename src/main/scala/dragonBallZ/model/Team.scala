package dragonBallZ.model

case class Team(name: String, warriors: List[Warrior]) extends Fighter[Team] {
  val energy: Int  = warriors.map(_.energy).sum

  def movementToUse: Option[Movement] = warriors match {
    case Nil => None
    case h::_ => h.movementToUse
  }

  override def substractEnergy(energy: Int): Team = this.warriors match {
    case Nil  => this
    case h::t => this.copy(warriors = h.fight(energy).map(_::t).getOrElse(t))
  }

  def compare(f: (Team, Option[Team]) => Int, vs: Option[Team]): Option[Team] =  f(this, vs) match {
    case 0  => None
    case 1  => Some(this)
    case -1 => vs
  }

  override def toString: String = name +": " + warriors.map(f => f.name + ": life -> " + f.lifePoints + " | energy -> " + f.energy)
}
