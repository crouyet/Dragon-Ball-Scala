package dragonBallZ.model

case class Activity(name: String, energy: Int) extends Fighter {

  override def substractEnergy(vs: Fighter): Activity = this.copy(energy= this.energy - vs.energy)

  override def fight(vs: Fighter): Option[Activity] =
    Option(this.substractEnergy(vs)).filter(_.energy > 0)
}
