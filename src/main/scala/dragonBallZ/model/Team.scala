package dragonBallZ.model

case class Team(name: String, warriors: List[Fighter]) extends Group with Fighter {
  val energy: Int  = warriors.map(_.energy).sum

  def substractEnergy(vs: Fighter): Team = this.warriors match {
    case Nil  => this
    case h::t => this.copy(warriors = h.fight(vs).map(_::t).getOrElse(t))
  }

  def compare(f: (Team, Team) => Int, vs: Team): Option[Team] =  f(this, vs) match {
    case 0  => None
    case 1  => Some(this)
    case -1 => Some(vs)
  }

  override def getFighters: List[Fighter] = this.warriors
}
