package dragonBallZ

case class Team(name: String, warriors: List[Fighter]) extends Fighter {
  val energy: Int  = warriors.map(_.energy).sum

  override def fight(vs: Fighter): Option[Team] = Option(this.substractEnergy(vs)).filter(_.energy > 0)

  def substractEnergy(vs: Fighter): Team = this.warriors match {
    case Nil  => this
    case h::t => this.copy(warriors = h.fight(vs).map(_::t).getOrElse(t))
  }

  def compare(f: (Team, Team) => Int, vs: Team): Option[Team] =  f(this, vs) match {
    case 0  => None
    case 1  => Some(this)
    case -1 => Some(vs)
  }

}

case class Warrior(name: String, energy: Int) extends Fighter {
  override def substractEnergy(vs: Fighter): Warrior = this.copy(energy = this.energy - vs.energy)
}


case class Mision(name: String, activities: List[Fighter]) extends Fighter {
  val energy: Int  = activities.map(_.energy).sum

  def substractEnergy(vs: Fighter): Mision = this.activities match {
    case Nil  => this
    case h::t => this.copy(activities = h.fight(vs).map(_::t).getOrElse(t))
  }
}

case class Activity(name: String, energy: Int) extends Fighter {
  override def substractEnergy(vs: Fighter): Activity = this.copy(energy= this.energy - vs.energy)
}